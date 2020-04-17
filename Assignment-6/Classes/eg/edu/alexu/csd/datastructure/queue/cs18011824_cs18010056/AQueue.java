package eg.edu.alexu.csd.datastructure.queue.cs18011824_cs18010056;

public class AQueue implements IQueue,IArrayBased {
    private Object [] Q;
    private int front,end,size,n;
    AQueue(int n){
        if(n<1) throw new RuntimeException();
        this.n=n;
        Q=new Object[n];
        front=0;end=0;size=0;
    }
    @Override
    public void enqueue(Object item) {
        if(size==n)throw new IndexOutOfBoundsException();
        Q[end]=item;
        end=(end+1)%n;
        ++size;

    }

    @Override
    public Object dequeue() {
        if(size==0)throw new NullPointerException();
        Object temp=Q[front];
        front=(front+1)%n;
        --size;
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }
    public Object front(){
        if(size==0)throw new NullPointerException();
        return Q[front];
    }
}
