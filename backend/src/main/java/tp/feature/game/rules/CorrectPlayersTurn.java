package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;
import tp.model.Piece;

import java.util.List;

public class CorrectPlayersTurn implements Rule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        if(!game.getCurrentPlayer().equals(move.piece())) {
            throw new CorrectPlayersTurn.Exception(move.piece());
        }
    }

    public static class Exception extends RuleBrokenException {
        public Exception(Piece piece) {
            super(String.format("It is not the turn of %s player", piece.toString()));
        }
    }
}
