package tp.feature.player.requestHandlers;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;
import tp.communication.MessageType;
import tp.communication.RequestMessageHandler;
import tp.communication.WebsocketExtensions;
import tp.feature.player.PlayerRegistry;
import tp.model.messages.request.RequestRegister;
import tp.model.messages.response.ResponseRegister;

@Controller
@ExtensionMethod({ WebsocketExtensions.class })
public class RegisterHandler implements RequestMessageHandler<RequestRegister> {
    private final PlayerRegistry playerRegistry;

    @Autowired
    public RegisterHandler(PlayerRegistry playerRegistry) {
        this.playerRegistry = playerRegistry;
    }

    @Override
    public void onMessage(RequestRegister message, WebSocketSession sender) {
        String newName = message.getContent();
        playerRegistry.renamePlayer(sender, newName);
        sender.sendResponse(new ResponseRegister(newName));
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.REGISTER;
    }
}
