package tp.feature.game.rules;

import tp.feature.game.Game;
import tp.model.Move;

import java.util.ArrayList;
import java.util.Collection;

public class GameRuleApplicator {
    protected ArrayList<Rule> ruleList = new ArrayList<>();

    public void applyMove(Game game, Move move) throws RuleBrokenException {
        for (Rule rule : ruleList) {
            rule.apply(game, move);
        }
    }

    public void addRule(Rule rule) {
        this.ruleList.add(rule);
    }

    public void addAllRules(Collection<Rule> rules) {
        this.ruleList.addAll(rules);
    }
}
