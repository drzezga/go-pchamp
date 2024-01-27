package tp.feature.game.rules;

public class RuleBrokenException extends RuntimeException {

    public RuleBrokenException() { }

    public RuleBrokenException(String message) {
        super(message);
    }
}
