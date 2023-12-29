package tp.game;

import tp.game.core.Piece;
import tp.game.core.Position;

public interface GameEventListener {
    void piecePlayed(Position pos, Piece piece);

    void turnPassed(Piece piece);

    void gameFinished();

    void gameStarted();
}
