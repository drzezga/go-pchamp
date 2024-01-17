package tp.player;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Optional;

@Component
public class PlayerRegistry {
    private HashMap<String, WebSocketSession> playerNameToWebsocketSessionMap = new HashMap<>();
    private HashMap<WebSocketSession, String> websocketSessionToPlayerNameMap = new HashMap<>();

    public void registerPlayer(WebSocketSession websocket, String playerName) {
        playerNameToWebsocketSessionMap.put(playerName, websocket);
        websocketSessionToPlayerNameMap.put(websocket, playerName);
    }

    public Optional<WebSocketSession> getWebsocketByPlayerName(String playerName) {
        return Optional.ofNullable(playerNameToWebsocketSessionMap.get(playerName));
    }

    public Optional<WebSocketSession> getPlayerNameByWebsocket(String playerName) {
        return Optional.ofNullable(playerNameToWebsocketSessionMap.get(playerName));
    }
}
