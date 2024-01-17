package tp.messages.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tp.model.messages.request.RequestGameTryMove;
import tp.model.Position;
import tp.model.messages.request.RequestMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestGameTryMoveTest {

    @Test
    void deserializePlaceStone() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String source = "{\"msg\":\"GAME_TRY_MOVE\",\"content\":{\"position\":[10,3]}}";
        RequestGameTryMove rgtm = (RequestGameTryMove) objectMapper.readValue(source, RequestMessage.class);
        assertEquals(new Position(10, 3), rgtm.getPosition());
    }

    @Test
    void deserializePassTurn() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String source = "{\"msg\":\"GAME_TRY_MOVE\",\"content\":null}";
        RequestGameTryMove rgtm = (RequestGameTryMove) objectMapper.readValue(source, RequestMessage.class);
        assertEquals(null, rgtm.getPosition());
    }
}
