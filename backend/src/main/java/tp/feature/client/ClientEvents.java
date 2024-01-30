package tp.feature.client;

import lombok.Getter;
import org.springframework.stereotype.Component;
import tp.events.ServerEvent;

@Getter
@Component
public class ClientEvents {
    private final ServerEvent<Client> clientConnectEvent = new ServerEvent<>();
    private final ServerEvent<Client> clientDisconnectEvent = new ServerEvent<>();
}
