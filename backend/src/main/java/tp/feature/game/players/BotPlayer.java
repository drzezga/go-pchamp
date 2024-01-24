package tp.feature.game.players;

import tp.feature.game.Game;
import tp.feature.game.GameController;

public class BotPlayer implements Player {
    private final GameController controller;

    public BotPlayer(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void makeMove(Game game) {
        // TODO: Implement bot playing
    }
}
