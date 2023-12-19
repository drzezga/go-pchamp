package tp.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Player {
    protected GameAPI game = null;
    protected String name;
}
