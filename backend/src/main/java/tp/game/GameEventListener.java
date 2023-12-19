package tp.game;

public interface GameEventListener {
    void piecePlayed(Position pos, Piece piece);

    void turnPassed(Piece piece);

    // TODO: Finish this
    void gameFinished();
}
