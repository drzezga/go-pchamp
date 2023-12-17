package tp.game;

import lombok.Builder;

import java.util.ArrayList;

public class GameBuilder {
    private int boardSize = 19;
    private final ArrayList<Player> players = new ArrayList<>();

    public GameBuilder boardSize(int boardSize) {
        this.boardSize = boardSize;

        return this;
    }

    public GameBuilder withPlayer(Player player) {
        this.players.add(player);

        return this;
    }

    public Game build() {
        Board board = new Board(boardSize);
        return new Game(players, board);
    }
}
