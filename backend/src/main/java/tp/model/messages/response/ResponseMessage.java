package tp.model.messages.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    @JsonProperty(value = "msg", index = 0)
    public MessageType messageType;
    public MessageStatus status;
    public String error;
    public T content;

}
