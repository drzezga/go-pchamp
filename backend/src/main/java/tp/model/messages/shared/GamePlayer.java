package tp.model.messages.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GamePlayer {
    private String name;
    private Float score;
}
