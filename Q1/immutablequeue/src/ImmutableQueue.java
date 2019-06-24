import java.util.NoSuchElementException;

public class ImmutableQueue<T> implements Queue<T> {
    private final ImmutableStack<T> inStack;
    private final ImmutableStack<T> outStack;

    public static final ImmutableQueue EMPTY_QUEUE = new ImmutableQueue<>();

    public ImmutableQueue() {
        this.inStack = ImmutableStack.EMPTY_STACK;
        this.outStack = ImmutableStack.EMPTY_STACK;
    }

    public ImmutableQueue(ImmutableStack<T> inStack, ImmutableStack<T> outStack) {
        this.inStack = inStack;
        this.outStack = outStack;
    }

    @Override
    public Queue<T> enQueue(T t) {
        if (t == null) {
            throw new IllegalArgumentException();
        }
        return new ImmutableQueue<T>(inStack.push(t), outStack);
    }

    public static final <T> ImmutableQueue<T> emptyQueue() {
        return (ImmutableQueue<T>) EMPTY_QUEUE;
    }

    @Override
    public Queue<T> deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size() == 1) {
            return emptyQueue();
        }
        if (outStack.isEmpty()) {
            ImmutableStack<T> stack = inStack.reverse();
            return new ImmutableQueue<T>(ImmutableStack.EMPTY_STACK, stack.pop());
        }
        return new ImmutableQueue<T>(inStack, outStack.pop());
    }

    private int size() {
        return inStack.size() + outStack.size();
    }

    @Override
    public T head() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (outStack.isEmpty()) {
            ImmutableStack<T> stack = inStack.reverse();
            return stack.head();
        }
        return outStack.head();
    }

    @Override
    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
