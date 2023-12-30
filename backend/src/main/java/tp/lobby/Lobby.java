package tp.lobby;

import tp.game.Player;

public class Lobby {
    public String name;
    public Player host = null;

    public Lobby(String name) {
        this.name = name;
    }
}
