package tp.feature.game;

import org.junit.jupiter.api.Test;
import tp.feature.game.rules.CannotPlaceStoneOnOccupiedTile;
import tp.feature.game.rules.CorrectPlayersTurn;
import tp.model.Board;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    private Board createBoardFromArray(int[][] boardElements) {
        var board = new Board(boardElements.length);

        for(int x = 0; x < board.getSize(); x++) {
            for(int y = 0; y < board.getSize(); y++) {
                if(boardElements[y][x] == 1) {
                    board.setPiece(x, y, Piece.WHITE);
                }

                if(boardElements[y][x] == 2) {
                    board.setPiece(x, y, Piece.BLACK);
                }
            }
        }

        return board;
    }

    @Test
    public void cannotPlacePieceOnOccupiedTile() {
        Board board = createBoardFromArray(new int[][] {
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

        Game game = new Game();
        game.setBoard(board);

        game.makeMove(new Move(
                Optional.of(new Position(0, 0)),
                Piece.BLACK
        ));
        assertThrows(
                CannotPlaceStoneOnOccupiedTile.Exception.class,
                () -> game.makeMove(new Move(
                        Optional.of(new Position(0, 0)),
                        Piece.WHITE
                ))
        );

        game.makeMove(new Move(
                Optional.of(new Position(0, 1)),
                Piece.WHITE
        ));
        assertThrows(
                CannotPlaceStoneOnOccupiedTile.Exception.class,
                () -> game.makeMove(new Move(
                        Optional.of(new Position(0, 0)),
                        Piece.BLACK
                ))
        );

        assertThrows(
                CannotPlaceStoneOnOccupiedTile.Exception.class,
                () -> game.makeMove(new Move(
                        Optional.of(new Position(0, 1)),
                        Piece.WHITE
                ))
        );
    }

    @Test
    public void cannotPlayTwiceInARow() {
        Board board = createBoardFromArray(new int[][] {
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
        Game game = new Game();
        game.setBoard(board);

        game.makeMove(new Move(
                Optional.of(new Position(0, 0)),
                Piece.BLACK
        ));

        assertThrows(
                CorrectPlayersTurn.Exception.class,
                () -> game.makeMove(new Move(
                        Optional.of(new Position(0, 1)),
                        Piece.BLACK
                ))
        );
    }

    @Test
    public void blackPlayerStartsGame() {
        Board board = createBoardFromArray(new int[][] {
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

        Game game = new Game();
        game.setBoard(board);

        assertThrows(
                CorrectPlayersTurn.Exception.class,
                () -> game.makeMove(new Move(
                        Optional.of(new Position(0, 1)),
                        Piece.WHITE
                ))
        );

        game.makeMove(new Move(
                Optional.empty(),
                Piece.BLACK
        ));
    }


    @Test
    public void skippingTwiceEndsGame() {
        Board board = createBoardFromArray(new int[][] {
                {0, 0},
                {0, 0},
        });
        Game game = new Game();
        game.setBoard(board);

        assertFalse(game.isFinished());
        game.makeMove(new Move(Optional.empty(), Piece.BLACK));
        assertFalse(game.isFinished());
        game.makeMove(new Move(Optional.empty(), Piece.WHITE));
        assertTrue(game.isFinished());
    }


}
