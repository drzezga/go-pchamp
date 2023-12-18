package tp.game;

import lombok.Getter;

@Getter
public record Move(Position position, Piece piece) {

}
