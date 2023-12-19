package tp.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class MessageController extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            InboundMessage msg = mapper.readValue(message.toString(), InboundMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
