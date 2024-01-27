package tp.feature.game;

import lombok.Getter;
import lombok.Setter;
import tp.feature.game.rules.*;
import tp.model.Board;
import tp.model.Move;
import tp.model.Piece;
import tp.model.messages.shared.GameSettings;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Game {
    private Board board;
    private final GameRuleApplicator rules = new GameRuleApplicator();
    private GameSettings gameSettings;
    private List<Move> moves = new ArrayList<>();

    private int numConsecutiveSkips = 0;
    private boolean isFinished = false;

    private Piece currentPlayer = Piece.BLACK;
    private String whitePlayerName;
    private String blackPlayerName;

    public Game() {
        rules.addRule(new MustPlayWithinBounds());
        rules.addRule(new CannotPlaceStoneOnOccupiedTile());
        rules.addRule(new CorrectPlayersTurn());
        rules.addRule(new PlayingAddsAStone());
        rules.addRule(new MoveIsRecorded());
        rules.addRule(new CurrentPlayerIsSwitched());
        rules.addRule(new SkippingMechanic());
    }

    public void makeMove(Move move) {
        rules.applyMove(this, move);
    }
}
