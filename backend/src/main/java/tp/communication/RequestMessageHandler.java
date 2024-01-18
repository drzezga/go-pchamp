package tp.communication;

import tp.feature.player.Player;
import tp.model.messages.request.RequestMessage;

public interface RequestMessageHandler<T extends RequestMessage> {
    void onMessage(T message, Player player);

    default void onMessageInternal(RequestMessage message, Player player) {
        this.onMessage((T)message, player);
    }

    MessageType getMessageType();
}
