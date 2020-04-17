package eg.edu.alexu.csd.datastructure.queue.cs18011824_cs18010056;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class AQueueTest {
    @Test
    public void queue() {
        AQueue q=new AQueue(10);

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

        ////////////////////////////////

        AQueue q1=new AQueue(5);
        q1.enqueue(0);
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);
        q1.enqueue(4);
        assertEquals(q1.size(),5);
        Assertions.assertThrows(IndexOutOfBoundsException .class, ()->q1.enqueue(5));
        q1.dequeue();
        q1.dequeue();
        q1.dequeue();
        q1.dequeue();
        q1.dequeue();
        assertEquals(q1.size(),0);
        Assertions.assertThrows(NullPointerException .class, q1::dequeue);
        q1.enqueue(1);
        q1.enqueue(2);
        q1.dequeue();
        q1.dequeue();
        q1.enqueue(3);
        q1.enqueue(3);
        q1.enqueue(3);
        q1.enqueue(3);
        q1.enqueue(3);
        Assertions.assertThrows(IndexOutOfBoundsException .class, ()->q1.enqueue(5));

    }

    @Test
    public void indexing() {
        AQueue q=new AQueue(0);
        assertTrue(q.isEmpty());
    }

    @Test
    public void T_1() {
        AQueue QA = new AQueue(3);
        QA.enqueue("ALAHLY");
        QA.enqueue("HALA MADRID");
        assertEquals("ALAHLY", QA.size());
        QA.dequeue();
        QA.dequeue();
        assertTrue(QA.isEmpty());
    }
}