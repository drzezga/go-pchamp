package tp.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class Player implements GameEventListener {
    protected GameAPI game = null;
    protected String name;
}
