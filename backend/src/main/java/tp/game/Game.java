package tp.game;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public record Game(ArrayList<Player> players, Board board) {

}
