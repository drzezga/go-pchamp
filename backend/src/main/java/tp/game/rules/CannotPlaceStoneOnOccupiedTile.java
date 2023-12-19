package tp.game.rules;

import tp.game.Board;
import tp.game.Game;
import tp.game.Move;

public class CannotPlaceStoneOnOccupiedTile implements Rule {
    @Override
    public Board modify(Board board, Move move) throws RuleBrokenException {
        return null;
    }
}
