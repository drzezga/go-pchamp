package tp.feature.client.requestHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tp.communication.MessageStatus;
import tp.communication.MessageType;
import tp.communication.RequestMessageHandler;
import tp.feature.client.Client;
import tp.feature.client.ClientRepository;
import tp.model.messages.request.RequestRegister;
import tp.model.messages.response.ResponseRegister;

@Controller
public class RegisterHandler implements RequestMessageHandler<RequestRegister> {
    private final ClientRepository clientRepository;

    @Autowired
    public RegisterHandler(ClientRepository playerRegistry) {
        this.clientRepository = playerRegistry;
    }

    @Override
    public void onMessage(RequestRegister message, Client sender) {
        String newName = message.getContent();

        if(clientRepository.getClientByName(newName).isPresent()) {
            sender.getMessageChannel().sendResponse(
                    new ResponseRegister(
                            MessageStatus.NOT_OK,
                            "ERROR: Player with specified name already exists"
                    )
            );
            return;
        }

        clientRepository.renamePlayer(sender, newName);
        sender.getMessageChannel().sendResponse(new ResponseRegister(MessageStatus.OK, newName));
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.REGISTER;
    }
}
