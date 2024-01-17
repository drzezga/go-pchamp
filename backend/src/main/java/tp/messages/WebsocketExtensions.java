package tp.messages;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Log
public class WebsocketExtensions {
    public static <T extends ResponseMessage> void sendResponse(WebSocketSession websocket, T response) {
        var objectMapper = new ObjectMapper();
        try {
            websocket.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
        } catch (IOException e) {
            log.severe("Sending response message failed");
        }
    }
}
