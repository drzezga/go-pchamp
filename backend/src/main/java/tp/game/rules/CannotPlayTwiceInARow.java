package tp.game.rules;

import tp.game.Board;
import tp.game.Game;
import tp.game.Move;

public class CannotPlayTwiceInARow implements Rule {
    @Override
    public Board modify(Board board, Move move) throws RuleBrokenException {
        return null;
    }
}
