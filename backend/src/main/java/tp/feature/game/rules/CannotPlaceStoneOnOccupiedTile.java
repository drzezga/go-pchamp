package tp.feature.game.rules;

import tp.model.Board;
import tp.model.Move;

public class CannotPlaceStoneOnOccupiedTile implements Rule {
    @Override
    public void modify(Board board, Move move) throws RuleBrokenException {
        System.out.println(board.getPiece(move.position()));
        if (board.getPiece(move.position()) != null) {
            throw new RuleBrokenException("Cannot place stone on occupied tile!");
        }
    }
}
