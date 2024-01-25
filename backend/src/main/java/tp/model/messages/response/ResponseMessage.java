package tp.model.messages.response;

import lombok.*;
import tp.communication.MessageStatus;
import tp.communication.MessageType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    public MessageType messageType;
    public MessageStatus status;
    public String error;
    public T content;

}
