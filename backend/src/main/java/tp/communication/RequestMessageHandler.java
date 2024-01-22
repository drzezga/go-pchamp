package tp.communication;

import tp.feature.client.Client;
import tp.model.messages.request.RequestMessage;

public interface RequestMessageHandler<T extends RequestMessage> {
    void onMessage(T message, Client player);

    default void onMessageInternal(RequestMessage message, Client player) {
        this.onMessage((T)message, player);
    }

    MessageType getMessageType();
}
