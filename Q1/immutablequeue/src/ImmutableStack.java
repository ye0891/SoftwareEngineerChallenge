import java.util.NoSuchElementException;

public final class ImmutableStack<T> {

    public final static ImmutableStack EMPTY_STACK = new ImmutableStack<>();

    private final T front;
    private final ImmutableStack<T> tail;
    private final int size;


    public ImmutableStack() {
        front = null;
        tail = null;
        size = 0;
    }

    private ImmutableStack(T f, ImmutableStack<T> t) {
        front = f;
        tail = t;
        size = tail.size() + 1;
    }

    public T head() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return front;
    }

    public ImmutableStack<T> reverse() {
        if (isEmpty()) {
            return new ImmutableStack<T>();
        }
        ImmutableStack<T> newStack = EMPTY_STACK;

        ImmutableStack<T> currentStack = this;
        while (!currentStack.isEmpty()) {
            newStack = newStack.push(currentStack.head());
            currentStack = currentStack.pop();
        }
        return newStack;

    }

    public ImmutableStack<T> push(T t) {
        if (t == null) {
            throw new IllegalArgumentException();
        }
        return new ImmutableStack<T>(t, this);

    }

    public ImmutableStack<T> pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
