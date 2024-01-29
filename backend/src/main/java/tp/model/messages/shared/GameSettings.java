package tp.model.messages.shared;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameSettings implements Serializable {
    private Integer size;
    private Boolean botOpponent;
    private String startingPlayer;
}
