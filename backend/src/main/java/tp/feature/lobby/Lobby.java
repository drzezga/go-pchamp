package tp.feature.lobby;

import lombok.*;

import java.util.Optional;

@Getter
@Setter(AccessLevel.PACKAGE)
@ToString
@AllArgsConstructor
public class Lobby {
    String lobbyName;
    Optional<String> host = Optional.empty();
    Optional<String> guest = Optional.empty();

    public Lobby(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public int getPlayerCount() {
        int count = 0;

        if(host.isPresent()) {
            count++;
        }

        if(guest.isPresent()) {
            count++;
        }

        return count;
    }
}
