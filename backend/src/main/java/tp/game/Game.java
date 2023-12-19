package tp.game;

import lombok.Getter;
import tp.game.rules.RuleBrokenException;
import tp.game.rules.RuleValidator;
import tp.util.Observer;

import java.util.ArrayList;
import java.util.Map;

@Getter
public class Game extends Observer<GameEventListener> {
    private final Board board;
    private final RuleValidator rules = new RuleValidator();
    private final ArrayList<Move> moves = new ArrayList<>();

    public Game(Board board, Map<Piece, String> playerNames) {
        this.board = board;
    }

    public void addStandardRuleset() {

    }

    public void makeMove(Move move) throws RuleBrokenException {
        Board movedBoard = rules.validate(board.clone(), move);
    }
}
