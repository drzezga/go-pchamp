package tp.communication;

import org.springframework.web.socket.WebSocketSession;
import tp.model.messages.request.RequestMessage;

public interface RequestMessageHandler<T extends RequestMessage> {
    void onMessage(T message, WebSocketSession sender);

    default void onMessageInternal(RequestMessage message, WebSocketSession sender) {
        this.onMessage((T)message, sender);
    }

    MessageType getMessageType();
}
