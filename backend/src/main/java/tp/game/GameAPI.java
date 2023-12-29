package tp.game;

import lombok.AllArgsConstructor;
import tp.game.core.Move;
import tp.game.core.Piece;
import tp.game.core.Position;
import tp.game.core.rules.RuleBrokenException;

import java.util.Optional;

@AllArgsConstructor
public class GameAPI {
    private Piece piece;
    private Game game;

    public void placeStone(Position position) throws RuleBrokenException {
        game.makeMove(new Move(position, piece));
    }

    public void passTurn() throws RuleBrokenException {
        game.makeMove(new Move(null, piece));
    }

    public Optional<String> getPlayerByPiece(Piece piece) {
        return game.getPiecePlayer(piece);
    }
}
