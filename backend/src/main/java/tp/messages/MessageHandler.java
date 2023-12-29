package tp.messages;

public interface MessageHandler<T extends RequestMessage> {
    void onMessage(T message);

    default void onMessageInternal(RequestMessage message) {
        this.onMessage((T)message);
    }

    MessageType getMessageType();
}
