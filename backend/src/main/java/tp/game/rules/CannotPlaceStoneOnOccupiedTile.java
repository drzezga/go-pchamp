package tp.game.rules;

import tp.game.Board;
import tp.game.Game;
import tp.game.Move;

public class CannotPlaceStoneOnOccupiedTile implements Rule {
    @Override
    public void modify(Board board, Move move) throws RuleBrokenException {
        System.out.println(board.getPiece(move.position()));
        if (board.getPiece(move.position()) != null) {
            throw new RuleBrokenException("Cannot place stone on occupied tile!");
        }
    }
}
