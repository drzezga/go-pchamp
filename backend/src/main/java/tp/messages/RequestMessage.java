package tp.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import tp.messages.request.RequestGameTryMove;
import tp.messages.request.RequestLobbyList;
import tp.messages.request.RequestLobbyStatus;
import tp.messages.request.RequestRegister;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "msg")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RequestRegister.class, name = "REGISTER"),
        @JsonSubTypes.Type(value = RequestLobbyList.class, name = "LOBBY_LIST"),
        @JsonSubTypes.Type(value = RequestLobbyStatus.class, name = "LOBBY_STATUS"),
        @JsonSubTypes.Type(value = RequestGameTryMove.class, name = "GAME_TRY_MOVE"),
})
public abstract class RequestMessage {
    public abstract MessageType getType();
}
