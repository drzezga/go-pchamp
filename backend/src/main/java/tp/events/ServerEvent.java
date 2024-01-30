package tp.events;

import java.util.ArrayList;
import java.util.List;

public class ServerEvent<T> {
    private List<EventHandler<T>> handlerList = new ArrayList<>();

    public void subscribe(EventHandler<T> handler) {
        handlerList.add(handler);
    }

    public void dispatch(T event) {
        for(EventHandler<T> handler : handlerList) {
            handler.handleEvent(event);
        }
    }
}
