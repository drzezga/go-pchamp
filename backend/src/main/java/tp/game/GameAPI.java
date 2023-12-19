package tp.game;

import tp.game.rules.RuleBrokenException;

public interface GameAPI {
    void placeStone(Position position) throws RuleBrokenException;

    void passTurn();
}
