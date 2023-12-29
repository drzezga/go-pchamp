package tp.game.core.rules;


import tp.game.core.Board;
import tp.game.core.Move;

public class PlayingAddsAStone implements Rule {
    @Override
    public void modify(Board board, Move move) {
        if (move.position() != null) {
            board.setPiece(move.position(), move.piece());
        }
    }
}
