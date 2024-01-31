package tp.feature.game.scoreCalculation;

import lombok.Getter;
import org.springframework.stereotype.Component;
import tp.feature.game.GameState;
import tp.model.Board;
import tp.model.Piece;

@Component
public class GameScoreCalculator {

    public GameScores calculateScores(GameState gameState) {
        float whiteScore = 6.5f;
        float blackScore = 0f;

        FloodFiller floodFiller = new FloodFiller();
        floodFiller.calculateScores(gameState.getCurrentBoardState());

        whiteScore += floodFiller.whiteScore;
        blackScore += floodFiller.blackScore;

        whiteScore -= gameState.getNumberPiecesCapturedByBlack();
        blackScore -= gameState.getNumberPiecesCapturedByWhite();

        return new GameScores(whiteScore, blackScore);
    }


    private class FloodFiller {
        @Getter
        private int whiteScore;
        @Getter
        private int blackScore;

        private int[][] resultBoard;


        private void calculateScores(Board gameBoard) {
            resultBoard = new int[gameBoard.getSize()][gameBoard.getSize()];

            for (int i = 0; i < resultBoard.length; i++) {
                for (int j = 0; j < resultBoard.length; j++) {
                    floodFill(i, j, new boolean[gameBoard.getSize()][gameBoard.getSize()], gameBoard);
                }
            }

            for (int i = 0; i < gameBoard.getSize(); i++) {
                for (int j = 0; j < gameBoard.getSize(); j++) {
                    if (resultBoard[i][j] == 0b01) {
                        whiteScore++;
                    }
                    if (resultBoard[i][j] == 0b10) {
                        blackScore++;
                    }
                }
            }
        }

        private int floodFill(int x, int y, boolean[][] visited, Board gameBoard) {
            if (x < 0 || x >= gameBoard.getSize() || y < 0 || y >= gameBoard.getSize()) {
                return 0b00;
            }

            if (visited[x][y]) {
                return resultBoard[x][y];
            }

            visited[x][y] = true;

            if (gameBoard.getPiece(x, y) == Piece.WHITE) {
                return 0b01;
            }
            if (gameBoard.getPiece(x, y) == Piece.BLACK) {
                return 0b10;
            }

            resultBoard[x][y] |= floodFill(x + 1, y, visited, gameBoard)
                    | floodFill(x - 1, y, visited, gameBoard)
                    | floodFill(x, y + 1, visited, gameBoard)
                    | floodFill(x, y - 1, visited, gameBoard);
            return resultBoard[x][y];
        }
    }
}
