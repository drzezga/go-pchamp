package tp.feature.client;

import lombok.Getter;

@Getter
public class Client {
    private ClientMessageChannel messageChannel;
    private String name;

    public Client(ClientMessageChannel messageChannel, String name) {
        this.messageChannel = messageChannel;
        this.name = name;
    }
}
