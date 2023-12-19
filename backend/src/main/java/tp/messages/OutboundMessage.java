package tp.messages;

import lombok.Getter;

@Getter
public class OutboundMessage {
    protected String msg;
    protected MessageStatus status;
}
