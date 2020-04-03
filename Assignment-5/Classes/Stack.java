package eg.edu.alexu.csd.datastructure.stack;

import java.util.EmptyStackException;

/***
 * the node class is the keystone for the stack as it holds the data and the pointer to the next node
 * its attributes are :
 * data which are an object
 * next which points to the next node
 */
class Node{
    Object data;
    Node next;
    Node(Object data,Node next){
        this.data=data;
        this.next=next;
    }
}

/***
 *The Stack class has methods pop,push,peek,size and isEmpty
 * and all are done in O(1)
 */
public class Stack implements IStack {
    private Node top;
    private int size;
    Stack(){
        Node top=null;
        size=0;
    }

    /***
     * the pop methods remove the top of the stack in O(1)
     * @exception EmptyStackException if the stack is empty
     * @return the popped data
     */
    @Override
    public Object pop() {
        if(top==null)throw new EmptyStackException();
        Object data=top.data;
        top=top.next;
        --size;
        return data;
    }

    /***
     * @return the top element  in O(1)
     * @exception if the stack is empty
     */

    @Override
    public Object peek() {
        if(top==null)throw new EmptyStackException();
        return top.data;
    }

    /***
     * the push method pushes the object to the top of the stack in O(1)
     * @param element
     */

    @Override
    public void push(Object element) {
        Node newnode=new Node(element,top);
        ++size;
        top=newnode;
    }

    /**
     * @return if the stack is empty or not
     * time complexity : O(1)
     */

    @Override
    public boolean isEmpty() {
        if(top==null)return true;
        return false;
    }

    /**
     * @return the size of the stack
     * time complexity : O(1)
     */
    @Override
    public int size() {
        return size;
    }
}
