package tp.feature.game.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tp.feature.game.Game;
import tp.model.Board;
import tp.model.Move;
import tp.model.Piece;
import tp.model.Position;

import java.util.ArrayList;
import java.util.List;

public class CapturingMechanic implements Rule {
    private static final List<Position> NEIGHBOR_OFFSETS = List.of(
            new Position( 0, 1),
            new Position( 0,-1),
            new Position( 1, 0),
            new Position(-1, 0)
    );

    @Override
    public void apply(Game game, Move move) throws RuleBrokenException {
        Board board = game.getBoard();
        List<PieceGroup> groups = getPieceGroups(board)
                .stream().filter(group -> group.getColor() != move.piece())
                .toList();

        for(PieceGroup group : groups) {
            if(!shouldGroupBeCaptured(group, board)) {
                continue;
            }

            captureGroup(group, board);

            int groupSize = group.getPositions().size();
            switch(move.piece()) {
                case BLACK -> game.setNumberPiecesCapturedByBlack(game.getNumberPiecesCapturedByBlack() + groupSize);
                case WHITE -> game.setNumberPiecesCapturedByWhite(game.getNumberPiecesCapturedByWhite() + groupSize);
            }
        }
    }

    private List<PieceGroup> getPieceGroups(Board board) {
        List<PieceGroup> groups = new ArrayList<>();

        boolean[][] visited = new boolean[board.getSize()][board.getSize()];

        for(int x = 0; x < board.getSize(); x++) {
            for(int y = 0; y < board.getSize(); y++) {
                ArrayList<Position> positions = visit(new Position(x, y), visited, board);

                if(positions.size() == 0) {
                    continue;
                }

                groups.add(new PieceGroup(
                        board.getPiece(x, y),
                        positions
                ));
            }
        }

        return groups;
    }

    private ArrayList<Position> visit(Position position, boolean[][] visited, Board board) {
        if(visited[position.y()][position.x()]) {
            return new ArrayList<>();
        }

        visited[position.y()][position.x()] = true;
        Piece piece = board.getPiece(position.x(), position.y());

        if(piece == null) {
            return new ArrayList<>();
        }

        var group = new ArrayList<Position>();
        group.add(position);

        for(Position offset : NEIGHBOR_OFFSETS) {
            Position neighborPosition = new Position(
                    position.x() + offset.x(),
                    position.y() + offset.y()
            );

            if(isPositionOutOfBounds(neighborPosition, board)) {
                continue;
            }

            if(board.getPiece(neighborPosition) != piece) {
                continue;
            }

            group.addAll(visit(neighborPosition, visited, board));
        }

        return group;
    }


    private void addToExistingOrCreateNewGroup(List<PieceGroup> groups, Position position, Piece color) {
        for(PieceGroup group : groups) {
            if(!group.getColor().equals(color)) {
                continue;
            }

            for(Position otherPosition : group.getPositions()) {
                int xDiff = Math.abs(position.x() - otherPosition.x());
                int yDiff = Math.abs(position.y() - otherPosition.y());

                if(xDiff * yDiff != 0) {
                    continue;
                }

                if(xDiff + yDiff > 1) {
                    continue;
                }

                group.getPositions().add(position);
                return;
            }
        }

        var arrayList = new ArrayList<Position>();
        arrayList.add(position);

        groups.add(new PieceGroup(
                color,
                arrayList
        ));
    }

    private boolean shouldGroupBeCaptured(PieceGroup group, Board board) {
        for(Position position : group.getPositions()) {
            if(!isPositionCaptured(position, group.getColor(), board)) {
                return false;
            }
        }

        return true;
    }

    private boolean isPositionCaptured(Position position, Piece groupColor, Board board) {
        if(isSurroundedBySameColorPieces(position, groupColor, board)) {
            return true;
        }


        Piece opponentColor = switch(groupColor) {
            case BLACK -> Piece.WHITE;
            case WHITE -> Piece.BLACK;
        };

        for(Position offset : NEIGHBOR_OFFSETS) {
            Position otherPosition = new Position(
                    position.x() + offset.x(),
                    position.y() + offset.y()
            );

            if(isPositionOutOfBounds(otherPosition, board)) {
                continue;
            }

            if(board.getPiece(otherPosition) == null) {
                return false;
            }
        }

        return true;
    }

    private boolean isSurroundedBySameColorPieces(Position position, Piece color, Board board) {
        for(Position offset : NEIGHBOR_OFFSETS) {
            Position otherPosition = new Position(
                    position.x() + offset.x(),
                    position.y() + offset.y()
            );

            if(isPositionOutOfBounds(otherPosition, board)) {
                continue;
            }

            if(board.getPiece(otherPosition) != color) {
                return false;
            }
        }

        return true;
    }

    private boolean isPositionOutOfBounds(Position position, Board board) {
        int size = board.getSize();

        if(position.x() < 0 || position.y() < 0) {
            return true;
        }

        if(position.x() >= size || position.y() >= size) {
            return true;
        }

        return false;
    }

    private void captureGroup(PieceGroup group, Board board) {
        for(Position position : group.getPositions()) {
            board.setPiece(position, null);
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class PieceGroup {
        private Piece color;
        private List<Position> positions;
    }
}
