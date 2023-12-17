package tp.game;

import lombok.Getter;

@Getter
public class Board {
    private final int size;
    private final Piece[][] pieces;

    public Board(int size) {
        this.size = size;
        this.pieces = new Piece[size][size];
    }

    public void setPiece(Position pos, Piece piece) {
        pieces[pos.x()][pos.y()] = piece;
    }

    public void clearPiece(Position pos) {
        pieces[pos.x()][pos.y()] = null;
    }

}
