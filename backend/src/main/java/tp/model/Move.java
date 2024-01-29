package tp.model;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Optional;

public record Move(@Nullable Position position, Piece piece) implements Serializable {

}
