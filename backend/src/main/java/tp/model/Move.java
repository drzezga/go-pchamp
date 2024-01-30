package tp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Optional;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Move implements Serializable {
    private Position position;
    private Piece piece;
}
