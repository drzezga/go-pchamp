package tp.model.messages.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "msg")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResponseRegister.class, name = "REGISTER"),
        @JsonSubTypes.Type(value = ResponseLobbyList.class, name = "LOBBY_LIST"),
        @JsonSubTypes.Type(value = ResponseLobbyStatus.class, name = "LOBBY_STATUS"),
        @JsonSubTypes.Type(value = ResponseGameStart.class, name = "GAME_START"),
        @JsonSubTypes.Type(value = ResponseGameLeave.class, name = "GAME_LEAVE"),
        @JsonSubTypes.Type(value = ResponseGameTryMove.class, name = "GAME_TRY_MOVE"),
        @JsonSubTypes.Type(value = ResponseGameMove.class, name = "GAME_MOVE"),
        @JsonSubTypes.Type(value = ResponseGameFinished.class, name = "GAME_FINISHED"),
        @JsonSubTypes.Type(value = ResponseReplayList.class, name = "REPLAY_LIST"),
        @JsonSubTypes.Type(value = ResponseReplayGet.class, name = "REPLAY_GET"),
})
public abstract class ResponseMessage {
    public MessageStatus status;

    public ResponseMessage(MessageStatus status) {
        this.status = status;
    }
}
