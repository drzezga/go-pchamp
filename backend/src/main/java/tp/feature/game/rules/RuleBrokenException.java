package tp.feature.game.rules;

public class RuleBrokenException extends Exception {
    RuleBrokenException(String content) {
        super(content);
    }
}
