package tp.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tp.game.Position;
import tp.messages.request.RequestGameTryMove;
import tp.messages.response.ResponseGameMove;

import java.io.IOException;

@Controller
public class MessageController extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestMessage msg = objectMapper.readValue(message.getPayload(), RequestMessage.class);

            if (msg instanceof RequestGameTryMove) {
                Position pos = ((RequestGameTryMove) msg).getPosition();

                TextMessage returnMessage = new TextMessage(objectMapper.writeValueAsBytes(new ResponseGameMove(MessageStatus.OK, "pbo", pos)));
                session.sendMessage(returnMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
