package tp.feature.game;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.SerializationUtils;
import tp.feature.game.rules.*;
import tp.model.Board;
import tp.model.Move;
import tp.model.Piece;
import tp.model.messages.shared.GameSettings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Game implements Serializable, Cloneable {
    private Board currentBoardState;

    private Board previousBoardState;
    private Board boardStateBeforeRuleApplication;

    private GameRuleApplicator rules = new GameRuleApplicator();
    private GameSettings gameSettings;
    private List<Move> moves = new ArrayList<>();

    private int numConsecutiveSkips = 0;
    private boolean isFinished = false;

    private Piece currentPlayer = Piece.BLACK;
    private String whitePlayerName;
    private String blackPlayerName;

    private int numberPiecesCapturedByWhite = 0;
    private int numberPiecesCapturedByBlack = 0;

    public Game(int size) {
        currentBoardState = new Board(size);
        previousBoardState = new Board(size);
        boardStateBeforeRuleApplication = new Board(size);

        rules.addRule(new BoardStateBeforeRuleApplicationRecorder());
        rules.addRule(new MustPlayWithinBounds());
        rules.addRule(new CannotPlaceStoneOnOccupiedTile());
        rules.addRule(new CorrectPlayersTurn());
        rules.addRule(new PlayingAddsAStone());
        rules.addRule(new MoveIsRecorded());
        rules.addRule(new CurrentPlayerIsSwitched());
        rules.addRule(new TurnSkippingMechanic());
        rules.addRule(new CapturingMechanic());
        rules.addRule(new KoRule());
        rules.addRule(new PreviousBoardStateRecorder());
    }

    @Override
    protected Game clone() {
        return SerializationUtils.clone(this);
    }

    public void makeMove(Move move) {
        rules.tryApplyMove(this, move);
    }
}
