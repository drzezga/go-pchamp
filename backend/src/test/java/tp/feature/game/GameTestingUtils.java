package tp.feature.game;

import tp.model.Board;
import tp.model.Piece;

public class GameTestingUtils {

    public static Board createBoardFromStrings(int[][] boardElements) {
        var board = new Board(boardElements.length);

        for(int x = 0; x < board.getSize(); x++) {
            for(int y = 0; y < board.getSize(); y++) {
                if(boardElements[y][x] == 1) {
                    board.setPiece(x, y, Piece.BLACK);
                }

                if(boardElements[y][x] == 2) {
                    board.setPiece(x, y, Piece.WHITE);
                }
            }
        }

        return board;
    }

    public static Game createGameFromArray(int[][] boardElements) {
        Game game = new Game();
        game.setBoard(createBoardFromStrings(boardElements));
        return game;
    }

}
