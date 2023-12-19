package tp.util;

import java.util.ArrayList;

public abstract class Observer<T> {
    protected ArrayList<T> listeners;

    public void addListener(T listener) {
        this.listeners.add(listener);
    }

    public void removeListener(T listener) {
        this.listeners.remove(listener);
    }
}
