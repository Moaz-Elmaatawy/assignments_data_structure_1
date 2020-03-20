package eg.edu.alexu.csd.datastructure.linkedList.cs;
//Ahmed Akram->18010056------Moaz Nabil->18011824
class Double_Node{
    Object data;
    Double_Node next;
    Double_Node prev;
    Double_Node(){}
    Double_Node(Object data){
        this.data=data;
        this.next=null;
        this.prev=null;
    }
}
public class Doubly_linked_list implements ILinkedList {
    private Double_Node head;
    private Double_Node tail;
    private int SIZE;
    Doubly_linked_list(){
        head=null;
        tail=null;
        SIZE=0;
    }
    public void add(int index, Object element) {
        if(index>SIZE||index<0  ){
            throw new NullPointerException(" already empty or out of bound");
            //System.out.println(index+" is Out of boundaries of the list !!");//if the index out of boundaries of the list
        }
        else{
            Double_Node newnode=new Double_Node(element);
            if(SIZE==0){head=tail=newnode;}
            else {
                if(index==0){head.prev=newnode;newnode.next=head;head=newnode;}//if it inserts at the front
                else if(index==SIZE){tail.next=newnode;newnode.prev=tail;tail=newnode;}//inserts at the back
                else {
                    Double_Node node = head;
                    for (int i = 0; i < index - 1; ++i) {
                        node = node.next;
                    }
                    newnode.prev=node;
                    newnode.next = node.next;
                    node.next.prev=newnode;
                    node.next = newnode;
                }
            }
            ++SIZE;
        }
    }

    public void add(Object x) {
        Double_Node newnode=new Double_Node(x);
        if(tail==null) {
            head = tail = newnode;
        }
        else{
            newnode.prev=tail;
            tail.next=newnode;
            tail=newnode;
        }
        ++SIZE;
    }

    public Object get(int index) {
        if(index>=SIZE||index<0){throw new NullPointerException(" already empty or out of bound");}//System.out.println(index+" is Out of boundaries of the list !!");return null;}
        else {
            Double_Node node = head;
            for (int i = 0; i < index; ++i) {
                node = node.next;
            }
            return node.data;
        }

    }

    public void set(int index, Object element) {
        if(index>=SIZE||index<0){throw new NullPointerException(" already empty or out of bound");}//System.out.println(index+" is Out of boundaries of the list !!");}
        else {
            Double_Node node = head;
            for (int i = 0; i < index; ++i) {
                node = node.next;
            }
            node.data=element;
        }

    }

    public void clear() {
        if(SIZE==0)throw new NullPointerException(" already empty");
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
        else if(SIZE==1){head=tail=null;--SIZE;}//clear list
        else {
            if(index==0){head=head.next;head.prev=null;}//remove the first
            else if(index==SIZE-1){tail=tail.prev;tail.next=null;}//remove the last element
            else {
                Double_Node node = head;
                for (int i = 0; i < index - 1; ++i) {
                    node = node.next;
                }
                node.next.next.prev=node;
                node.next = node.next.next;
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
            throw new NullPointerException(" already empty or out of bound");
            //System.out.println("out of boundaries of the list");
            //return s;
        }
        Double_Node n=head;
        for (int i=0;i<=toIndex;++i){
            if(i>=fromIndex)
                s.add(n.data);
            n=n.next;
        }
        return s;
    }

    public boolean contains(Object o) {
        boolean flage=false;
        Double_Node n=head;
        while (n!=null) {
            if(n.data==o){flage=true;break;}
            n=n.next;
        }
        return flage;
    }
    public void print(){
        Double_Node n=head;
        while (n!=null) {
            System.out.print(n.data+",");
            n=n.next;
        }
    }
}
