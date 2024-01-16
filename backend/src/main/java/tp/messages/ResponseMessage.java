package tp.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import tp.messages.response.ResponseGameMove;
import tp.messages.response.ResponseGameTryMove;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "msg")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResponseGameMove.class, name = "GAME_MOVE"),
        @JsonSubTypes.Type(value = ResponseGameTryMove.class, name = "GAME_TRY_MOVE")
})
public abstract class ResponseMessage {
    public MessageType msg;
    public MessageStatus status;

    public ResponseMessage(MessageType messageType, MessageStatus status) {
        this.msg = messageType;
        this.status = status;
    }
}
