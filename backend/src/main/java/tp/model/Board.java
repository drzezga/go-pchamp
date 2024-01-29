package tp.model;

import lombok.Getter;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Arrays;

@Getter

public class Board implements Serializable, Cloneable {
    private final int size;
    private final Piece[][] pieces;

    public Board(int size) {
        this.size = size;
        this.pieces = new Piece[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.pieces[i][j] = null;
            }
        }
    }

    public void setPiece(Position pos, Piece piece) {
        pieces[pos.x()][pos.y()] = piece;
    }

    public void setPiece(int x, int y, Piece piece) {
        pieces[x][y] = piece;
    }

    public void clearPiece(Position pos) {
        pieces[pos.x()][pos.y()] = null;
    }

    public Piece getPiece(Position pos) {
        return pieces[pos.x()][pos.y()];
    }

    public Piece getPiece(int x, int y) {
        return pieces[x][y];
    }

    @Override
    public Board clone() {
        return SerializationUtils.clone(this);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();

        for(int y = 0; y < size; y++) {
            builder.append('[');

            for(int x = 0; x < size; x++) {
                Piece piece = getPiece(x, y);

                if(piece == null) {
                    builder.append(' ');
                }
                if(piece == Piece.BLACK) {
                    builder.append('B');
                }
                if(piece == Piece.WHITE) {
                    builder.append('W');
                }

                if(x < size - 1) {
                    builder.append(' ');
                }
            }

            builder.append("]\n");
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.deepEquals(pieces, board.pieces);
    }
}
