package eg.edu.alexu.csd.datastructure.queue.cs18011824_cs18010056;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.*;

public class LQueueTest {
    @Test
    public void queue() {
        LQueue q=new LQueue();

        assertEquals(q.size(),0);
        assertTrue(q.isEmpty());
        Assertions.assertThrows(NullPointerException .class, q::dequeue);

        int input_0=0;
        q.enqueue(input_0);
        assertEquals((int)q.front(),input_0);
        assertEquals(q.size(),1);
        assertFalse(q.isEmpty());

        int input_1=1;
        q.enqueue(input_1);
        assertEquals((int)q.front(),input_0);
        assertEquals(q.size(),2);
        assertFalse(q.isEmpty());

        int input_2=2;
        q.enqueue(input_2);
        assertEquals((int)q.front(),input_0);
        assertEquals(q.size(),3);
        assertFalse(q.isEmpty());


        assertEquals((int)q.dequeue(),input_0);
        assertEquals(q.size(),2);
        assertFalse(q.isEmpty());

        assertEquals((int)q.dequeue(),input_1);
        assertEquals(q.size(),1);
        assertFalse(q.isEmpty());

        assertEquals((int)q.dequeue(),input_2);
        assertEquals(q.size(),0);
        assertTrue(q.isEmpty());

        Assertions.assertThrows(NullPointerException .class, q::dequeue);

    }

}