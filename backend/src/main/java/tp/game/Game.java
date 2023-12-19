package tp.game;

import lombok.Getter;
import tp.game.rules.RuleValidator;
import tp.util.Observer;

import java.util.ArrayList;
import java.util.Optional;

@Getter
public class Game extends Observer<GameEventListener> {
    private final Board board;
    private final RuleValidator rules = new RuleValidator();
    private final ArrayList<Move> moves = new ArrayList<>();

    public Game(Board board) {
        this.board = board;
    }

    public void addStandardRuleset() {

    }

    public void makeMove(Move move) {
        if ()
        if (move.position() == null) {

        }
    }
}
