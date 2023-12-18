package tp.game.rules;

import tp.game.Board;
import tp.game.Move;

import java.util.List;
import java.util.Optional;

public class RuleValidator {
    List<Rule> ruleList;

    public Optional<Board> validate(Board board, Move move) {
        return ruleList.stream().reduce(Optional.of(board), (b, r) -> b.isEmpty() ? b : r.modify(b.get(), move), (oldBoard, newBoard) -> newBoard);
    }
}
