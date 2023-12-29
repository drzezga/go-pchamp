package tp.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import tp.game.core.Piece;
import tp.game.core.Position;
import tp.messages.MessageStatus;
import tp.messages.ResponseMessage;
import tp.messages.response.ResponseGameMove;

import java.io.IOException;

@Getter
@Setter
@Component
@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WebsocketPlayer extends Player {
    private WebSocketSession session = null;

    @Override
    public void piecePlayed(Position pos, Piece piece) {
        ObjectMapper objectMapper = new ObjectMapper();
        // TODO: CHANGE THIS GET
        ResponseMessage msg = new ResponseGameMove(MessageStatus.OK, game.getPlayerByPiece(piece).get(), pos);
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(msg)));
        } catch (IOException ignored) { }
    }

    @Override
    public void turnPassed(Piece piece) {
        ObjectMapper objectMapper = new ObjectMapper();
        // TODO: CHANGE THIS GET
        ResponseMessage msg = new ResponseGameMove(MessageStatus.OK, game.getPlayerByPiece(piece).get(), null);
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(msg)));
        } catch (IOException ignored) { }
    }

    @Override
    public void gameFinished() {
        // TODO: implement this
    }

    @Override
    public void gameStarted() {
        // TODO: implement this
    }

    public void sendMessage(ResponseMessage message) {
        // TODO: 29/12/2023 Finish this method 
    }
}
