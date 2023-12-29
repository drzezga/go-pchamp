package tp.lobby;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LobbyController {
    List<Lobby> listLobbies() {
        return new ArrayList<>();
    }
}
