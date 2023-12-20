package tp.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tp.game.*;
import tp.game.rules.RuleBrokenException;
import tp.messages.request.RequestGameTryMove;
import tp.messages.response.ResponseGameMove;
import tp.messages.response.ResponseGameTryMove;

import java.io.IOException;
import java.util.Map;

@Log
@Controller
public class MessageController extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        WebsocketPlayer player = (WebsocketPlayer) session.getAttributes().get("player");
        if (player == null) {
            player = new WebsocketPlayer();
            player.setSession(session);
            player.setName("pbo");

            Game game = new Game(new Board(19));

            game.addStandardRuleset();

            player.setGame(game.addPlayer(player, Piece.BLACK));
            log.info("Creating new game");
            session.getAttributes().put("player", player);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestMessage msg = objectMapper.readValue(message.getPayload(), RequestMessage.class);

            log.info("Received message");

            if (msg instanceof RequestGameTryMove) {
                Position pos = ((RequestGameTryMove) msg).getPosition();

                try {
                    if (pos == null) {
                        player.getGame().passTurn();
                    } else {
                        player.getGame().placeStone(pos);
                    }
                } catch (RuleBrokenException e) {
                    TextMessage returnMessage = new TextMessage(objectMapper.writeValueAsBytes(new ResponseGameTryMove(e.getMessage())));
                    session.sendMessage(returnMessage);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
