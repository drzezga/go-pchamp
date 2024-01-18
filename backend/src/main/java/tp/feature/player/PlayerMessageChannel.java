package tp.feature.player;

import tp.model.messages.response.ResponseMessage;

public interface PlayerMessageChannel {
    <T extends ResponseMessage> void sendResponse(T response);
}
