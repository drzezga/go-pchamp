package tp.model;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Board implements Cloneable {
    private final int size;
    private final Piece[][] pieces;

    public Board(int size) {
        this.size = size;
        this.pieces = new Piece[size][size];
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
        Board board = new Board(this.size);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                board.setPiece(x, y, getPiece(x, y));
            }
        }
        return board;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();

        for(int y = 0; y < size; y++) {
            builder.append('[');

            for(int x = 0; x < size; x++) {
                boolean isPieceWhite = getPiece(x, y).equals(Piece.WHITE);
                builder.append(isPieceWhite ? 'W' : 'B');
                if(x < size - 1) {
                    builder.append(' ');
                }
            }

            builder.append("]\n");
        }

        return builder.toString();
    }


}
