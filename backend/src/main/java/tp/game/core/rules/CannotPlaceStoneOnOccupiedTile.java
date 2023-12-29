package tp.game.core.rules;

import tp.game.core.Board;
import tp.game.core.Move;

public class CannotPlaceStoneOnOccupiedTile implements Rule {
    @Override
    public void modify(Board board, Move move) throws RuleBrokenException {
        System.out.println(board.getPiece(move.position()));
        if (board.getPiece(move.position()) != null) {
            throw new RuleBrokenException("Cannot place stone on occupied tile!");
        }
    }
}
