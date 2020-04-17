package eg.edu.alexu.csd.datastructure.queue.cs18011824_cs18010056;

class Node {
    public Object entry;
    public Node next;
    public Node(final Object entry) {this.entry = entry ; next = null;}}

public class LQueue implements IQueue , ILinkedBased {
    private Node head = null , tail = null ;
    private int size = 0 ;

    @Override
    public void enqueue(final Object item) {
        //if (item == null) {throw new RuntimeException("Error!!");}
        Node e = new Node(item);
        if (head == null) { head = e;tail = e; }
        else { tail.next = e;tail = tail.next; }
        size++;
    }

    @Override
    public Object dequeue() {
        if (size == 0) {throw new RuntimeException("Error!!");}
        if (size == 1) {Object Q = head.entry ; head = head.next ; tail = head ; size-- ; return Q ;}
        Object Q = head.entry ; head = head.next; size--;
        return Q;}

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public int size() {return size;}
    public Object front(){
        if(size==0)throw new NullPointerException();
        return head.entry;
    }
}

