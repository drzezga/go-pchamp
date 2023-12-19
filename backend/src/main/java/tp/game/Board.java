package tp.game;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Board {
    private final int size;
    private final Piece[][] pieces;
    private ArrayList<Move> moves = new ArrayList<>();

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

    public Piece getPiece(Position pos) {
        return pieces[pos.x()][pos.y()];
    }

    public void addMove(Move move) {
        this.moves.add(move);
    }
}
