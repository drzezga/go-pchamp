package tp.communication;

import tp.feature.client.Client;
import tp.model.messages.request.RequestMessage;

public interface RequestMessageHandler<T extends RequestMessage> {
    void onMessage(T message, Client sender);

    default void onMessageInternal(RequestMessage message, Client sender) {
        this.onMessage((T)message, sender);
    }

    MessageType getMessageType();
}
