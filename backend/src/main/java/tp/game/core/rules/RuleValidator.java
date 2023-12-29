package tp.game.core.rules;

import tp.game.core.Board;
import tp.game.core.Move;

import java.util.ArrayList;
import java.util.Collection;

public class RuleValidator {
    protected ArrayList<Rule> ruleList = new ArrayList<>();

    public Board validate(Board board, Move move) throws RuleBrokenException {
        for (Rule rule : ruleList) {
            rule.modify(board, move);
        }
        return board;
    }

    public void addRule(Rule rule) {
        this.ruleList.add(rule);
    }

    public void addAllRules(Collection<Rule> rules) {
        this.ruleList.addAll(rules);
    }
}
