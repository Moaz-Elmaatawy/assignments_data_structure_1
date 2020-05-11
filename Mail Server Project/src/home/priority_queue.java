package home;

/***
 * pair class is a class holds two data type object and int
 */
class pair {
    Object item;
    int key;
    pair(Object item, int key){
        this.item=item;
        this.key=key;
    }
}

/**
 * priority_queue class implements the interface IpriorityQueue  
 */
public class priority_queue implements IPriorityQueue {
    Doubly_linked_list Q=new Doubly_linked_list();
    @Override
    public void insert(Object item, int key) {
        int index=0;
        for(int i=1;i<=Q.size();++i){
            if(((pair)Q.get(i-1)).key<=key)
                index=i;
            else
                break;
        }
        pair p = new pair(item,key);
        Q.add(index,p);
    }

    @Override
    public Object removeMin() {
        if(Q.isEmpty())throw new NullPointerException();
        Object item =((pair)Q.get(0)).item;
        Q.remove(0);
        return item;
    }

    @Override
    public Object min() {
        if(Q.isEmpty())throw new NullPointerException();
        return ((pair)Q.get(0)).item;
    }

    @Override
    public boolean isEmpty() {
        return Q.isEmpty();
    }

    @Override
    public int size() {
        return Q.size();
    }
}
