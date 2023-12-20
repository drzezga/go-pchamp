package tp.util;

import java.util.ArrayList;

public abstract class Observer<T> {
    protected ArrayList<T> listeners = new ArrayList<>();

    public void addListener(T listener) {
        this.listeners.add(listener);
    }

    public void removeListener(T listener) {
        this.listeners.remove(listener);
    }
}
