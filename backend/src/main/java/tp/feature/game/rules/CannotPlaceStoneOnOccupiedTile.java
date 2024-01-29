package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;

public class CannotPlaceStoneOnOccupiedTile implements GameRule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        if(move.position() == null) {
            return;
        }

        if(game.getCurrentBoardState().getPiece(move.position()) != null) {
            throw new CannotPlaceStoneOnOccupiedTile.Exception("Cannot place stone on occupied tile!");
        }
    }

    public static class Exception extends RuleBrokenException {
        public Exception(String message) {
            super(message);
        }
    }
}
