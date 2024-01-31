package tp.feature.game.bot;

import tp.feature.game.GameState;
import tp.model.Piece;
import tp.model.Position;
import tp.model.messages.shared.GameSettings;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SurroundStrategy implements BotStrategy {

    @Override
    public Position tryGenerateMovePosition(GameState gameState) throws RuntimeException {
        List<Position> opponentPositions = collectOpponentPositions(gameState);
        List<Position> opponentSurroundingPositions = collectSurroundingEmptyPositions(opponentPositions, gameState);

        if(opponentSurroundingPositions.size() == 0) {
            throw new CannotGenerateMoveException();
        }

        return opponentSurroundingPositions.get(0);
    }

    private List<Position> collectOpponentPositions(GameState gameState) {
        Piece opponentPiece = getOpponentPiece(gameState.getSettings());
        int boardSize = gameState.getSettings().getSize();

        ArrayList<Position> opponentPositions = new ArrayList<>();
        for(int x = 0; x < boardSize; x++) {
            for(int y = 0; y < boardSize; y++) {
                Piece piece = gameState.getCurrentBoardState().getPiece(x, y);
                if(piece == opponentPiece) {
                    opponentPositions.add(new Position(x, y));
                }
            }
        }

        return opponentPositions;
    }

    private List<Position> collectSurroundingEmptyPositions(List<Position> opponentPositions, GameState gameState) {
        List<Position> offsets = List.of(
                new Position(0, 1),
                new Position(0, -1),
                new Position(1, 0),
                new Position(-1, 0)
        );

        ArrayList<Position> surroundingPosition = new ArrayList<>();

        for(Position opponentPosition : opponentPositions) {
            for(Position offset : offsets) {
                Position position = new Position(
                        opponentPosition.x() + offset.x(),
                        opponentPosition.y() + offset.y()
                );
                if(isPositionOutOfBounds(position, gameState)) {
                    continue;
                }
                if(gameState.getCurrentBoardState().getPiece(position) != null) {
                    continue;
                }

                surroundingPosition.add(position);
            }
        }

        return surroundingPosition;
    }

    private boolean isPositionOutOfBounds(Position position, GameState gameState) {
        int boardSize = gameState.getSettings().getSize();

        if(position.x() < 0 || position.x() >= boardSize) {
            return true;
        }

        if(position.y() < 0 || position.y() >= boardSize) {
            return true;
        }

        return false;
    }


    private Piece getOpponentPiece(GameSettings gameSettings) {
        String startingPlayer = gameSettings.getStartingPlayer();

        if(startingPlayer.equals(BotController.BOT_CLIENT.getName())) {
            return Piece.WHITE;
        } else {
            return Piece.BLACK;
        }
    }
}
