package tp.feature.game.scoreCalculation;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class GameScores {
    private float whitePlayerScore;
    private float blackPlayerScore;
}
