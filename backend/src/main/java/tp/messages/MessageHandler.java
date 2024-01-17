package tp.messages;

import org.springframework.web.socket.WebSocketSession;

public interface MessageHandler<T extends RequestMessage> {
    void onMessage(T message, WebSocketSession sender);

    default void onMessageInternal(RequestMessage message, WebSocketSession sender) {
        this.onMessage((T)message, sender);
    }

    MessageType getMessageType();
}
