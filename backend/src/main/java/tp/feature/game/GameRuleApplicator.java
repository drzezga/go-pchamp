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

    public void tryApplyMove(Game game, Move move) throws RuleBrokenException {
        Game copy = game.clone();

        for (GameRule rule : ruleList) {
            rule.apply(copy, move);
        }

        applyChangesSinceNoErrorWasThrown(copy, game);
    }

    private void applyChangesSinceNoErrorWasThrown(Game source, Game target) {
        BeanUtils.copyProperties(source, target);
    }

    public void addRule(GameRule rule) {
        this.ruleList.add(rule);
    }

    public void addAllRules(Collection<GameRule> rules) {
        this.ruleList.addAll(rules);
    }
}
