package tp.feature.game.persistence;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import tp.model.Move;
import tp.model.messages.shared.GamePlayer;
import tp.model.messages.shared.GameSettings;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "replays")
public class GameRecord {
    @Id
    private String id;

    private String name;
    private GameSettings gameSettings;

    private List<GamePlayer> players;
    private List<Move> moves;
}
