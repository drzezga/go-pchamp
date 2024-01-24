package tp.model.messages.shared;

import lombok.Data;

@Data
public class GameSettings {
    private Integer size;
    private Boolean botOpponent;
    private String startingPlayer;
}
