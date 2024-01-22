package tp.feature.client;

import tp.model.messages.response.ResponseMessage;

public interface ClientMessageChannel {
    <T extends ResponseMessage> void sendResponse(T response);
}
