package tp.feature.game;

import org.springframework.beans.BeanUtils;
import tp.feature.game.rules.GameRule;
import tp.feature.game.rules.RuleBrokenException;
import tp.model.Move;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class GameRuleApplicator implements Serializable {
    protected ArrayList<GameRule> ruleList = new ArrayList<>();

    public void tryApplyMove(GameState game, Move move) throws RuleBrokenException {
        GameState copy = game.clone();

        for (GameRule rule : ruleList) {
            rule.apply(copy, move);
        }

        applyChangesSinceNoErrorWasThrown(copy, game);
    }

    private void applyChangesSinceNoErrorWasThrown(GameState source, GameState target) {
        BeanUtils.copyProperties(source, target);
    }

    public void addRule(GameRule rule) {
        this.ruleList.add(rule);
    }

    public void addAllRules(Collection<GameRule> rules) {
        this.ruleList.addAll(rules);
    }
}
