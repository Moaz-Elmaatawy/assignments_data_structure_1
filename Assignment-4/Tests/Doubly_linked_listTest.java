package eg.edu.alexu.csd.datastructure.linkedList.cs;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class Doubly_linked_listTest {
    @Test
    public void insertion_1() {
        Doubly_linked_list s1=new Doubly_linked_list();
        s1.add(5);
        assertSame(s1.get(0),5);
        s1.add(4);
        s1.add(3);
        s1.add(2);
        s1.add(1);
        assertSame(s1.get(1),4);
        assertSame(s1.get(2),3);
        assertSame(s1.get(4),1);
    }

    @Test
    public void insertion_2() {
        Doubly_linked_list s=new Doubly_linked_list();

        s.add(0,0);
        assertSame(s.get(0),0);

        s.add(0,1);
        assertSame(s.get(0),1);

        s.add(1,2);
        s.add(2,3);
        assertSame(s.get(1),2);
        assertSame(s.get(2),3);

        s.add(s.size(),4);
        assertSame(s.get(s.size()-1),4);

        /////Special cases//////////

        Assertions.assertThrows(NullPointerException .class,() ->{s.add(s.size()+1,4);});
        Assertions.assertThrows(NullPointerException .class,() ->{s.add(-1,55);});
        Assertions.assertThrows(NullPointerException .class,() ->{s.add(1000,55);});

    }

    @Test
    public void insertion_3() {
        Doubly_linked_list s=new Doubly_linked_list();
        s.add(0,0);
        s.add(1,1);
        s.add(2,2);
        s.add(3,3);

        s.add(2,22);
        assertSame(s.get(2),22);
        assertSame(s.get(3),2);

        s.add(1,11);
        assertSame(s.get(1),11);
        assertSame(s.get(2),1);
    }

    @Test
    public void set() {
        Doubly_linked_list s=new Doubly_linked_list();
        s.add(0,0);
        s.add(1,1);
        s.add(2,2);
        s.add(3,3);

        s.set(2,10);
        assertSame(s.get(2),10);
        assertSame(s.get(3),3);

        Assertions.assertThrows(NullPointerException .class,() ->{s.set(123,10);});
        Assertions.assertThrows(NullPointerException .class,() ->{s.set(-20,52);});
    }

    @Test
    public void sublist() {
        Doubly_linked_list s=new Doubly_linked_list();
        ILinkedList s1=new Doubly_linked_list();
        for(int i=0;i<10;++i)
            s.add(i);
        s1=s.sublist(3,8);
        assertSame(s1.size(),6);
        assertSame(s1.get(0),3);
        assertSame(s1.get(5),8);
        assertSame(s1.get(1),4);
        assertSame(s1.get(2),5);
    }

    @Test
    public void sublist_2() {
        Doubly_linked_list s=new Doubly_linked_list();
        ILinkedList s1=new Doubly_linked_list();
        s.add(5);
        s1=s.sublist(0,0);
        assertSame(s1.size(),1);
        assertSame(s1.get(0),5);
    }

    @Test
    public void remove() {
        Doubly_linked_list s=new Doubly_linked_list();
        s.add(0,0);
        s.add(1,1);
        s.add(2,2);
        s.add(3,3);

        assertSame(s.size(),4);
        s.remove(2);
        assertSame(s.size(),3);

        assertSame(s.get(0),0);
        assertSame(s.get(1),1);
        assertSame(s.get(2),3);

        s.remove(0);
        s.remove(0);
        s.remove(0);
        assertSame(s.size(),0);

        Assertions.assertThrows(NullPointerException .class,() ->{s.remove(0);});
    }

    @Test
    public void contains() {
        Doubly_linked_list s=new Doubly_linked_list();
        s.add(0,0);
        s.add(1,1);
        s.add(2,2);
        s.add(3,3);

        assertSame(s.contains(3),true);
        assertSame(s.contains(55),false);
    }

    @Test
    public void clear() {
        Doubly_linked_list s=new Doubly_linked_list();
        s.add(0,0);
        s.add(1,1);
        s.add(2,2);
        s.add(3,3);

        assertSame(s.isEmpty(),false);
        s.clear();
        assertSame(s.isEmpty(),true);
        Assertions.assertThrows(NullPointerException .class,() ->{s.clear();});
    }

}