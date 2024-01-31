package tp.feature.game;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.SerializationUtils;
import tp.feature.game.rules.*;
import tp.model.Board;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;
import tp.model.messages.shared.GameSettings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
public class GameState implements Serializable, Cloneable {
    private Board currentBoardState;
    private Board previousBoardState;
    private Board boardStateBeforeRuleApplication;

    private GameRuleApplicator rules = new GameRuleApplicator();
    private GameSettings settings;
    private List<Move> moves = new ArrayList<>();

    private int numConsecutiveSkips = 0;
    private boolean isFinished = false;

    private Piece currentPlayer = Piece.BLACK;

    private int numberPiecesCapturedByWhite = 0;
    private int numberPiecesCapturedByBlack = 0;

    public GameState(GameSettings settings) {
        this(settings.getSize());
        this.setSettings(settings);
    }

    public GameState(int size) {
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
    protected GameState clone() {
        return SerializationUtils.clone(this);
    }

    public List<Position> makeMove(Move move) {
        rules.tryApplyMove(this, move);

        Set<Move> capturedPieces = createMoveSetFromBoard(getPreviousBoardState());
        capturedPieces.removeAll(createMoveSetFromBoard(getCurrentBoardState()));
        capturedPieces.remove(move);

        return capturedPieces.stream().map(Move::getPosition).collect(Collectors.toList());
    }

    private Set<Move> createMoveSetFromBoard(Board board) {
        var moveSet = new HashSet<Move>();

        for(int x = 0; x < board.getSize(); x++) {
            for(int y = 0; y < board.getSize(); y++) {
                if(board.getPiece(x, y) == null) {
                    continue;
                }

                moveSet.add(new Move(
                        new Position(x, y),
                        board.getPiece(x, y)
                ));
            }
        }

        return moveSet;
    }
}
