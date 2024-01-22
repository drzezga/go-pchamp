package tp.feature.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import tp.model.messages.response.ResponseMessage;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
public class WebSocketMessageChannel implements ClientMessageChannel {
    private WebSocketSession websocket;

    public WebSocketMessageChannel(WebSocketSession websocket) {
        this.websocket = websocket;
    }

    @Override
    public <T extends ResponseMessage> void sendResponse(T response) {
        var objectMapper = new ObjectMapper();
        try {
            websocket.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
        } catch (IOException e) {
            log.severe("Sending response message failed");
        }
    }

    @Override
    public int hashCode() {
        return websocket.getId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebSocketMessageChannel that = (WebSocketMessageChannel) o;
        return websocket.getId().equals(that.websocket.getId());
    }
}
