package tp.events;

public interface EventHandler<T> {
    void handleEvent(T event);
}
