package tp.game;

import lombok.AllArgsConstructor;
import tp.game.rules.RuleBrokenException;

@AllArgsConstructor
public class GamePlayer implements GameAPI {
    private Piece piece;
    private Game game;

    @Override
    public void placeStone(Position position) throws RuleBrokenException {

    }

    @Override
    public void passTurn() {

    }
}
