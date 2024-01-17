package tp.feature.game.core.rules;


import tp.model.Board;
import tp.model.Move;

public class PlayingAddsAStone implements Rule {
    @Override
    public void modify(Board board, Move move) {
        if (move.position() != null) {
            board.setPiece(move.position(), move.piece());
        }
    }
}
