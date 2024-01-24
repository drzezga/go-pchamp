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
import tp.feature.client.ClientRepository;
import tp.feature.client.WebSocketMessageChannel;
import tp.model.messages.request.RequestMessage;

import java.util.HashMap;
import java.util.List;

@Log
@Controller
public class MessageController extends TextWebSocketHandler {
    private final HashMap<MessageType, RequestMessageHandler<?>> handlers = new HashMap<>();
    private final ClientRepository playerRegistry;

    @Autowired
    public MessageController(List<RequestMessageHandler<?>> handlers, ClientRepository playerRegistry) {
        this.playerRegistry = playerRegistry;
        for (RequestMessageHandler<?> handler: handlers) {
            log.info("Registering handler for " + handler.getMessageType().toString());
            this.handlers.put(handler.getMessageType(), handler);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        playerRegistry.addPlayer(new Client(
                new WebSocketMessageChannel(session),
                session.getId()
        ));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Client player = playerRegistry.getPlayerByMessageChannel(new WebSocketMessageChannel(session)).get();
        playerRegistry.removePlayer(player);
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

        handler.onMessageInternal(msg, player);
    }

}