package tp.feature.game.rules;

import tp.feature.game.GameState;
import tp.model.Move;
import tp.model.Piece;

public class CurrentPlayerIsSwitched implements GameRule {
    @Override
    public void apply(GameState game, Move move) throws RuleBrokenException {
        Piece nextTurnPlayer = switch(game.getCurrentPlayer()) {
            case WHITE -> Piece.BLACK;
            case BLACK -> Piece.WHITE;
        };
        game.setCurrentPlayer(nextTurnPlayer);
    }
}
