package tp.feature.lobby;

import lombok.*;
import tp.feature.client.Client;

import java.util.Optional;

@Getter
@Setter(AccessLevel.PACKAGE)
@ToString
@AllArgsConstructor
public class Lobby {
    String lobbyName;
    Optional<Client> host = Optional.empty();
    Optional<Client> guest = Optional.empty();

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
