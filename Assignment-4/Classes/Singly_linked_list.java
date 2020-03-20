package eg.edu.alexu.csd.datastructure.linkedList.cs;
//Ahmed Akram->18010056------Moaz Nabil->18011824
class Single_Node{
    Object data;
    Single_Node next;
    Single_Node(){}
    Single_Node(Object data){
        this.data=data;
        this.next=null;
    }
}
public class Singly_linked_list implements ILinkedList {
    private Single_Node head;
    private Single_Node tail;
    private int SIZE;
    Singly_linked_list(){
        head=null;
        tail=null;
        SIZE=0;
    }
    public void add(int index, Object element) {
        if(index>SIZE||index<0  ){
            throw new NullPointerException(" Out of bound");//System.out.println(index+" is Out of boundaries of the list !!");
        }
        else{
            Single_Node newnode=new Single_Node(element);
            if(SIZE==0){head=tail=newnode;}//if the index out of boundaries of the list
            else {
                if(index==0){newnode.next=head;head=newnode;}//inserts at the front
                else if(index==SIZE){tail.next=newnode;tail=newnode;}//inserts at the back
                else {
                    Single_Node node = head;
                    for (int i = 0; i < index - 1; ++i) {
                        node = node.next;
                    }
                    newnode.next = node.next;
                    node.next = newnode;
                }
            }
            ++SIZE;
        }
    }

    public void add(Object x) {
        Single_Node newnode = new Single_Node(x);
        if(tail==null) {
            head = tail = newnode;
        }
        else{
            tail.next=newnode;
            tail=newnode;
        }
        ++SIZE;
    }

    public Object get(int index) {
        if(index>=SIZE||index<0){throw new NullPointerException("out of bound");}//System.out.println(index+" is Out of boundaries of the list !!");return null;}
        else if(index==0){return head.data;}
        else if(index==SIZE-1){return tail.data;}
        else {
            Single_Node newnode = head;
            for (int i = 0; i < index; ++i) {
                newnode = newnode.next;
            }
            return newnode.data;
        }

    }

    public void set(int index, Object element) {
        if(index>=SIZE||index<0){throw new NullPointerException(" already empty or out of bound");}//System.out.println(index+" is Out of boundaries of the list !!");}
        else {
            Single_Node newnode = head;
            for (int i = 0; i < index; ++i) {

                newnode = newnode.next;
            }
            newnode.data=element;
        }

    }

    public void clear() {
        if(SIZE==0)throw new NullPointerException("empty list");
        head=tail=null;
        SIZE=0;
    }

    public boolean isEmpty() {
        if(head==null)
            return true;
        else
            return false;
    }

    public void remove(int index) {
        if(index>=SIZE||index<0){throw new NullPointerException(" already empty or out of bound");}//System.out.println(index+" is Out of boundaries of the list !!");}
        else {
            if(index==0){head=head.next;}//remove the first element
            else {
                Single_Node newnode = head;
                for (int i = 0; i < index - 1; ++i) {
                    newnode = newnode.next;
                }
                if (index == SIZE - 1) { //remove the last element
                    tail = newnode; //set the tail
                    newnode.next = null;
                }
                else {
                    newnode.next = newnode.next.next;
                }
            }
            --SIZE;
        }
    }

    public int size() {
        return SIZE;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        Singly_linked_list s=new Singly_linked_list();
        if(fromIndex<0||fromIndex>=SIZE||toIndex<0||toIndex>=SIZE||toIndex<fromIndex) {
            throw new NullPointerException(" already empty or out of bound");//System.out.println("out of boundaries of the list");
            //return s;
        }
        Single_Node n=head;
        for (int i=0;i<=toIndex;++i){
            if(i>=fromIndex)
                s.add(n.data);
            n=n.next;
        }
        return s;
    }

    public boolean contains(Object o) {
        boolean flage=false;
        Single_Node n=head;
        while (n!=null) {
            if(n.data==o){flage=true;break;}
            n=n.next;
        }
        return flage;
    }
    public void print(){
        Single_Node n=head;
        while (n!=null) {
            System.out.print(n.data+",");
            n=n.next;
        }
    }
}
