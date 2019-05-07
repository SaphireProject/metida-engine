package metida.object;

import java.util.*;

public class QueueMethods<T> extends AbstractQueue<T> {

    private LinkedList<T> elements;

    public QueueMethods() {
        this.elements = new LinkedList<T>();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean offer(T t) {
        if(t == null) return false;
        elements.add(t);
        return true;
    }

    @Override
    public T poll() {
        Iterator<T> iter = elements.iterator();
        T t = iter.next();
        if(t != null){
            iter.remove();
            return t;
        }
        return null;
    }

    @Override
    public T peek() {
        return elements.getFirst();
    }

    @Override
    public String toString() {
        return "QueueMethods{" +
                "elements=" + elements +
                '}';
    }
}
