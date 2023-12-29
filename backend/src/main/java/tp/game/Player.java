package tp.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import tp.game.GameAPI;
import tp.game.GameEventListener;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class Player implements GameEventListener {
    protected GameAPI game = null;
    protected String name;
}
