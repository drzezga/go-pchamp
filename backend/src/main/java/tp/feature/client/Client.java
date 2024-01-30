package tp.feature.client;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Client {
    private ClientMessageChannel messageChannel;
    private String name;

    public Client(ClientMessageChannel messageChannel, String name) {
        this.messageChannel = messageChannel;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(messageChannel, client.messageChannel) && Objects.equals(name, client.name);
    }
}
