package tp.feature.player;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Optional;

@Component
public class PlayerRegistry {
    private HashMap<String, WebSocketSession> playerNameToWebsocketSessionMap = new HashMap<>();
    private HashMap<WebSocketSession, String> websocketSessionToPlayerNameMap = new HashMap<>();

    public void addPlayer(WebSocketSession websocket) {
        String playerName = websocket.getId();
        playerNameToWebsocketSessionMap.put(playerName, websocket);
        websocketSessionToPlayerNameMap.put(websocket, playerName);
    }

    public void renamePlayer(WebSocketSession websocket, String newName) {
        Optional<String> oldName = getPlayerNameByWebsocket(websocket);

        playerNameToWebsocketSessionMap.remove(oldName.get());

        playerNameToWebsocketSessionMap.put(newName, websocket);
        websocketSessionToPlayerNameMap.put(websocket, newName);
    }

    public void removePlayer(WebSocketSession websocket) {
        Optional<String> playerName = getPlayerNameByWebsocket(websocket);

        if(playerName.isEmpty()) {
            return;
        }

        playerNameToWebsocketSessionMap.remove(playerName.get());
        websocketSessionToPlayerNameMap.remove(websocket);
    }

    public Optional<WebSocketSession> getWebsocketByPlayerName(String playerName) {
        return Optional.ofNullable(playerNameToWebsocketSessionMap.get(playerName));
    }

    public Optional<String> getPlayerNameByWebsocket(WebSocketSession websocket) {
        return Optional.ofNullable(websocketSessionToPlayerNameMap.get(websocket));
    }
}
