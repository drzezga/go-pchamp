package tp.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tp.feature.client.Client;
import tp.feature.client.ClientEvents;
import tp.feature.client.ClientRepository;
import tp.feature.client.WebSocketMessageChannel;
import tp.model.messages.request.RequestMessage;
import tp.model.messages.response.ResponseMessage;

import java.util.HashMap;
import java.util.List;

@Log
@Controller
public class MessageController extends TextWebSocketHandler {
    private final HashMap<MessageType, RequestMessageHandler<?>> handlers = new HashMap<>();
    private final ClientRepository playerRegistry;
    private final ClientEvents clientEvents;

    @Autowired
    public MessageController(List<RequestMessageHandler<?>> handlers,
                             ClientRepository playerRegistry, ClientEvents clientEvents) {
        this.playerRegistry = playerRegistry;
        this.clientEvents = clientEvents;
        for (RequestMessageHandler<?> handler: handlers) {
            log.info("Registering handler for " + handler.getMessageType().toString());
            this.handlers.put(handler.getMessageType(), handler);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        clientEvents.getClientConnectEvent().dispatch(new Client(
                new WebSocketMessageChannel(session),
                session.getId()
        ));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Client client = playerRegistry.getPlayerByMessageChannel(new WebSocketMessageChannel(session)).get();
        clientEvents.getClientDisconnectEvent().dispatch(client);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestMessage msg;
        try {
            msg = objectMapper.readValue(message.getPayload(), RequestMessage.class);
        } catch (JsonProcessingException e) {
            // TODO: Send error message back
            log.severe("Could not parse incoming message: " + e.getMessage());
            return;
        }
        RequestMessageHandler<?> handler = handlers.get(msg.getType());
        if (handler == null) {
            log.severe("Could not find handler for message type " + msg.getType());
            return;
        }

        Client player = playerRegistry.getPlayerByMessageChannel(new WebSocketMessageChannel(session)).get();

        try {
            handler.onMessageInternal(msg, player);
        } catch(Exception e) {
            log.warning(String.format(
                    "An error occurred while processing message %s: %s",
                    msg.getType(),
                    e
            ));
            e.printStackTrace();
            player.getMessageChannel().sendResponse(
                    ResponseMessage.builder()
                            .messageType(msg.getType())
                            .status(MessageStatus.NOT_OK)
                            .error(e.getMessage())
                            .build()
            );
        }
    }

}
