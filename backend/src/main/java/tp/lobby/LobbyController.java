package tp.lobby;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LobbyController {
    public List<Lobby> listLobbies() {
        List<Lobby> list = new ArrayList<>();

        list.add(new Lobby("lobby1"));

        return list;
    }
}
