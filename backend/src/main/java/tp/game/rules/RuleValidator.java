package tp.game.rules;

import tp.game.Board;
import tp.game.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class RuleValidator {
    private ArrayList<Rule> ruleList;

    public Board validate(Board board, Move move) throws RuleBrokenException {
        Board acc = board;
        for (Rule rule : ruleList) {
            acc = rule.modify(acc, move);
        }
        return acc;
    }

    public void addRule(Rule rule) {
        this.ruleList.add(rule);
    }

    public void addAllRules(Collection<Rule> rules) {
        this.ruleList.addAll(rules);
    }
}
