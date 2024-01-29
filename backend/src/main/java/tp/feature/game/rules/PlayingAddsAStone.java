package tp.feature.game.rules;


import tp.feature.game.Game;
import tp.model.Board;
import tp.model.Move;

public class PlayingAddsAStone implements GameRule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        Board board = game.getCurrentBoardState();
        if (move.position() != null) {
            board.setPiece(move.position(), move.piece());
        }
    }
}
