import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class ImmutableQueueTest {

    @Test
    public void enQueueDeQueue01() {
        // enqueue->dequeue
        Queue<String> queue = new ImmutableQueue<>();
        queue = queue.enQueue("a").enQueue("b").enQueue("c");
        assertEquals("a", queue.head());
        queue = queue.deQueue();
        assertEquals("b", queue.head());
        queue = queue.deQueue();
        assertEquals("c", queue.head());
    }

    @Test
    public void enQueueDeQueue02() {
        // enqueue->dequeue->enqueue->dequeue
        Queue<String> queue = new ImmutableQueue<>();
        queue = queue.enQueue("a").enQueue("b").enQueue("c");
        queue = queue.deQueue().deQueue();
        queue = queue.enQueue("d").enQueue("e");
        assertEquals("c", queue.head());
        queue = queue.deQueue();
        assertEquals("d", queue.head());
    }

    @Test
    public void emptyQueue01() {
        // enqueue ->dequeue -> isEmpty
        Queue<String> queue = new ImmutableQueue<>();
        queue = queue.enQueue("a").enQueue("b");
        queue = queue.deQueue();
        assertFalse(queue.isEmpty());
        queue = queue.deQueue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void emptyQueueException() {
        Queue queue = new ImmutableQueue<>();
        assertThrows(NoSuchElementException.class, () ->  queue.head());
    }

    @Test
    public void enQueueException() {
        Queue queue = new ImmutableQueue<>();
        assertThrows(IllegalArgumentException.class, () ->  queue.enQueue(null));
    }
}