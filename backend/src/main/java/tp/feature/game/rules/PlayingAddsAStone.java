package tp.feature.game.rules;


import tp.feature.game.GameState;
import tp.model.Board;
import tp.model.Move;

public class PlayingAddsAStone implements GameRule {
    @Override
    public void apply(GameState game, Move move) throws RuleBrokenException {
        Board board = game.getCurrentBoardState();
        if (move.getPosition() != null) {
            board.setPiece(move.getPosition(), move.getPiece());
        }
    }
}
