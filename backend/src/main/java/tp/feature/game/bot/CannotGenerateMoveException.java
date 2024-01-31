package tp.feature.game.bot;

public class CannotGenerateMoveException extends RuntimeException {
    public CannotGenerateMoveException() {
        super("Cannot generate a valid move");
    }
}
