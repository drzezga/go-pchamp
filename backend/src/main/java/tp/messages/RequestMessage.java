package tp.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import tp.messages.request.RequestGameTryMove;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "msg")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RequestGameTryMove.class, name = "GAME_TRY_MOVE")
})
public abstract class RequestMessage {
    protected MessageType msg;
}
