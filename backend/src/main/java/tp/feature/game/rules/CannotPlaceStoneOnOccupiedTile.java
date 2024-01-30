package tp.feature.game.rules;

import tp.feature.game.GameState;
import tp.model.Move;

public class CannotPlaceStoneOnOccupiedTile implements GameRule {
    @Override
    public void apply(GameState game, Move move) throws RuleBrokenException {
        if(move.getPosition() == null) {
            return;
        }

        if(game.getCurrentBoardState().getPiece(move.getPosition()) != null) {
            throw new CannotPlaceStoneOnOccupiedTile.Exception("Cannot place stone on occupied tile!");
        }
    }

    public static class Exception extends RuleBrokenException {
        public Exception(String message) {
            super(message);
        }
    }
}
