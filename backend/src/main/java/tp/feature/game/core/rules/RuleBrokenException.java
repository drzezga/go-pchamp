package tp.feature.game.core.rules;

public class RuleBrokenException extends Exception {
    RuleBrokenException(String content) {
        super(content);
    }
}
