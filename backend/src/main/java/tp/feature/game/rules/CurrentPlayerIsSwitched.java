package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;
import tp.model.Piece;

public class CurrentPlayerIsSwitched implements GameRule {
    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        Piece nextTurnPlayer = switch(game.getCurrentPlayer()) {
            case WHITE -> Piece.BLACK;
            case BLACK -> Piece.WHITE;
        };
        game.setCurrentPlayer(nextTurnPlayer);
    }
}
