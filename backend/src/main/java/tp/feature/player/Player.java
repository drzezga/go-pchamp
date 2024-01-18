package tp.feature.player;

import lombok.Getter;

@Getter
public class Player {
    private PlayerMessageChannel messageChannel;
    private String name;

    public Player(PlayerMessageChannel messageChannel, String name) {
        this.messageChannel = messageChannel;
        this.name = name;
    }
}
