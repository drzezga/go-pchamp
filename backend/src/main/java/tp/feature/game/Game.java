package tp.feature.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tp.feature.client.Client;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private GameState gameState;
    private Client whitePlayer;
    private Client blackPlayer;
}
