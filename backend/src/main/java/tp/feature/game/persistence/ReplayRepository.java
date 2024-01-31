package tp.feature.game.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tp.feature.game.GameState;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplayRepository extends MongoRepository<GameRecord, String> {
    List<GameRecord> findAll();
    Optional<GameRecord> findById(String id);
    GameRecord save(GameRecord newRecord);
}
