package tp.model.messages.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import tp.communication.MessageType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "msg")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RequestRegister.class, name = "REGISTER"),
        @JsonSubTypes.Type(value = RequestLobbyList.class, name = "LOBBY_LIST"),
        @JsonSubTypes.Type(value = RequestLobbyStatus.class, name = "LOBBY_STATUS"),
        @JsonSubTypes.Type(value = RequestGameStart.class, name = "GAME_START"),
        @JsonSubTypes.Type(value = RequestGameLeave.class, name = "GAME_LEAVE"),
        @JsonSubTypes.Type(value = RequestGameTryMove.class, name = "GAME_TRY_MOVE"),
        @JsonSubTypes.Type(value = RequestGameMove.class, name = "GAME_MOVE"),
        @JsonSubTypes.Type(value = RequestReplayList.class, name = "REPLAY_LIST"),
        @JsonSubTypes.Type(value = RequestReplayGet.class, name = "REPLAY_GET"),
})
public abstract class RequestMessage {
    public abstract MessageType getType();
}
