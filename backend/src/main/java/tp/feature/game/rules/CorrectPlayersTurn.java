package tp.feature.game.rules;

import tp.feature.game.GameState;
import tp.model.Move;
import tp.model.Piece;

public class CorrectPlayersTurn implements GameRule {
    @Override
    public void apply(GameState game, Move move) throws RuleBrokenException {
        if(!game.getCurrentPlayer().equals(move.getPiece())) {
            throw new CorrectPlayersTurn.Exception(move.getPiece());
        }
    }

    public static class Exception extends RuleBrokenException {
        public Exception(Piece piece) {
            super(String.format("It is not the turn of %s player", piece.toString()));
        }
    }
}
