package tp.messages.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tp.model.messages.response.ResponseGameMove;
import tp.model.Position;
import tp.communication.MessageStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseGameMoveTest {

    @Test
    void serialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseGameMove rgm = new ResponseGameMove(MessageStatus.OK, "drzezga", new Position(10, 3));
        String expected = "{\"msg\":\"GAME_MOVE\",\"status\":\"ok\",\"content\":{\"player\":\"drzezga\",\"position\":[10,3]}}";
        assertEquals(expected, objectMapper.writeValueAsString(rgm));
    }
}
