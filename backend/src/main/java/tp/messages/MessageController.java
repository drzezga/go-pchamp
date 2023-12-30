package tp.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.List;

@Log
@Controller
public class MessageController extends TextWebSocketHandler {
    private final HashMap<MessageType, MessageHandler<?>> handlers = new HashMap<>();

    @Autowired
    public MessageController(List<MessageHandler<?>> handlers) {
        for (MessageHandler<?> handler: handlers) {
            log.info("Registering handler for " + handler.getMessageType().toString());
            this.handlers.put(handler.getMessageType(), handler);
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestMessage msg;
        try {
            msg = objectMapper.readValue(message.getPayload(), RequestMessage.class);
        } catch (JsonProcessingException e) {
            // TODO: Send error message back
            return;
        }
        MessageHandler<?> handler = handlers.get(msg.getType());
        if (handler == null) {
            log.severe("Could not find handler for message type " + msg.getType());
        } else {
            handler.onMessageInternal(msg);
        }
//        WebsocketPlayer player = (WebsocketPlayer) session.getAttributes().get("player");
//        if (player == null) {
//            player = new WebsocketPlayer();
//            player.setSession(session);
//            player.setName("pbo");
//
//            Game game = new Game(new Board(19));
//
//            game.addStandardRuleset();
//
//            player.setGame(game.addPlayer(player, Piece.BLACK));
//            log.info("Creating new game");
//            session.getAttributes().put("player", player);
//        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            RequestMessage msg = objectMapper.readValue(message.getPayload(), RequestMessage.class);
//
//            log.info("Received message");
//
//            if (msg instanceof RequestGameTryMove) {
//                Position pos = ((RequestGameTryMove) msg).getPosition();
//
//                try {
//                    if (pos == null) {
//                        player.getGame().passTurn();
//                    } else {
//                        player.getGame().placeStone(pos);
//                    }
//                } catch (RuleBrokenException e) {
//                    TextMessage returnMessage = new TextMessage(objectMapper.writeValueAsBytes(new ResponseGameTryMove(e.getMessage())));
//                    session.sendMessage(returnMessage);
//                }
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

}
