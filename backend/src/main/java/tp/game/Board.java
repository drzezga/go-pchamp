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

    public boolean canPlaceAt(Position pos) {
        return pieces[pos.x()][pos.y()] == null;
    }

    public void setPiece(Position pos, Piece piece) {
        pieces[pos.x()][pos.y()] = piece;
    }
}
