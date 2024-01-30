package tp.feature.game;

import org.junit.jupiter.api.Test;
import tp.feature.game.rules.CannotPlaceStoneOnOccupiedTile;
import tp.feature.game.rules.CorrectPlayersTurn;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {



    @Test
    public void cannotPlacePieceOnOccupiedTile() {
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        });

        game.makeMove(new Move(
                new Position(0, 0),
                Piece.BLACK
        ));
        assertThrows(
                CannotPlaceStoneOnOccupiedTile.Exception.class,
                () -> game.makeMove(new Move(
                        new Position(0, 0),
                        Piece.WHITE
                ))
        );

        game.makeMove(new Move(
                new Position(0, 1),
                Piece.WHITE
        ));
        assertThrows(
                CannotPlaceStoneOnOccupiedTile.Exception.class,
                () -> game.makeMove(new Move(
                        new Position(0, 0),
                        Piece.BLACK
                ))
        );

        assertThrows(
                CannotPlaceStoneOnOccupiedTile.Exception.class,
                () -> game.makeMove(new Move(
                        new Position(0, 1),
                        Piece.WHITE
                ))
        );
    }

    @Test
    public void cannotPlayTwiceInARow() {
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        });

        game.makeMove(new Move(
                new Position(0, 0),
                Piece.BLACK
        ));

        assertThrows(
                CorrectPlayersTurn.Exception.class,
                () -> game.makeMove(new Move(
                        new Position(0, 1),
                        Piece.BLACK
                ))
        );
    }

    @Test
    public void blackPlayerStartsGame() {
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        });

        assertThrows(
                CorrectPlayersTurn.Exception.class,
                () -> game.makeMove(new Move(
                        new Position(0, 1),
                        Piece.WHITE
                ))
        );

        game.makeMove(new Move(
                null,
                Piece.BLACK
        ));
    }


    @Test
    public void skippingTwiceEndsGame() {
        GameState game = GameTestingUtils.createGameFromArray(new int[][] {
                {0, 0},
                {0, 0},
        });

        assertFalse(game.isFinished());
        game.makeMove(new Move(null, Piece.BLACK));
        assertFalse(game.isFinished());
        game.makeMove(new Move(null, Piece.WHITE));
        assertTrue(game.isFinished());
    }


}
