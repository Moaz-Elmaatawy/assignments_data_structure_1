package eg.edu.alexu.csd.datastructure.linkedList.cs;
//Ahmed Akram->18010056------Moaz Nabil->18011824
import java.awt.*;
import java.util.Arrays;

public class Application implements IPolynomialSolver {
    Doubly_linked_list A=new Doubly_linked_list();
    Doubly_linked_list B=new Doubly_linked_list();
    Doubly_linked_list C=new Doubly_linked_list();
    Doubly_linked_list R=new Doubly_linked_list();
    public void setPolynomial(char poly, int[][] terms) {
        java.util.Arrays.sort(terms, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1]-b[1];
            }
        });

        if(poly=='A'){
            for (int i=0;i<terms.length;++i){
                Point p=new Point();
                p.setLocation(terms[i][0],terms[i][1]);
                A.add(0, p);
            }
        }
        else if(poly=='B'){
            for (int i=0;i<terms.length;++i){
                Point p=new Point();
                p.setLocation(terms[i][0],terms[i][1]);
                B.add(0, p);
            }
        }
        else if(poly=='C'){
            for (int i=0;i<terms.length;++i){
                Point p=new Point();
                p.setLocation(terms[i][0],terms[i][1]);
                C.add(0, p);
            }
        }
    }
    
    public String print(char poly) {
        String result=new String();
        if(poly=='A'){
            result+=("A is : ");
           for(int i=0;i<A.size();++i){
               Point p= (Point) A.get(i);
               if(p.x!=0){
                   result += (p.x == 1 ? "" : p.x) + "x^(" + p.y + ")";
                   if (i != A.size() - 1) result += "+ ";
               }
               else {
                   result += 0;
                   if (i != A.size() - 1) result += "+ ";
               }
           }
        }
        else if(poly=='B'){
            result+=("B is : ");
            for(int i=0;i<B.size();++i){
                Point p= (Point) B.get(i);
                if(p.x!=0){
                    result += (p.x == 1 ? "" : p.x) + "x^(" + p.y + ")";
                    if (i != B.size() - 1) result += "+ ";
                }
                else {
                    result += 0;
                    if (i != B.size() - 1) result += "+ ";
                }
            }
        }
        else if(poly=='C'){
            result+=("C is : ");
            for(int i=0;i<C.size();++i){
                Point p= (Point) C.get(i);
                if(p.x!=0){
                    result += (p.x == 1 ? "" : p.x) + "x^(" + p.y + ")";
                    if (i != C.size() - 1) result += "+ ";
                }
                else {
                    result += 0;
                    if (i != C.size() - 1) result += "+ ";
                }
            }
        }
        else if(poly=='R'){
            result+=("R is : ");
            for(int i=0;i<R.size();++i){
                Point p= (Point) R.get(i);
                if(p.x!=0){
                    result += (p.x == 1 ? "" : p.x) + "x^(" + p.y + ")";
                    if (i != R.size() - 1) result += "+ ";
                }
                else {
                    result += 0;
                    if (i != R.size() - 1) result += "+ ";
                }
            }
        }
        return result;
    }
    
    public void clearPolynomial(char poly) {
        if(poly=='A'){
            try {
                A.clear();
            }catch (Exception e){}
        }
        else if(poly=='B'){
            try {
                B.clear();
            }catch (Exception e){}
        }
        else if(poly=='C'){
            try {
                C.clear();
            }catch (Exception e){}
        }
        else if(poly=='R'){
            try {
                R.clear();
            }catch (Exception e){}
        }
    }
    
    public float evaluatePolynomial(char poly, float value) {
        float result=0;
        if(poly=='A'){
            for(int i=0;i<A.size();++i){
                Point p= (Point) A.get(i);
                result+=p.x*Math.pow(value,p.y);
            }
        }
        else if(poly=='B'){
            for(int i=0;i<B.size();++i){
                Point p= (Point) B.get(i);
                result+=p.x*Math.pow(value,p.y);
            }
        }
        else if(poly=='C'){
            for(int i=0;i<C.size();++i){
                Point p= (Point) C.get(i);
                result+=p.x*Math.pow(value,p.y);
            }
        }
        else{
            for(int i=0;i<R.size();++i){
                Point p= (Point) R.get(i);
                result+=p.x*Math.pow(value,p.y);
            }
        }
        return result;
    }
    
    public int[][] add(char poly1, char poly2) {
        try {
            R.clear();
        }catch (Exception e){}
        Doubly_linked_list temp1=new Doubly_linked_list();
        Doubly_linked_list temp2=new Doubly_linked_list();
        if(poly1=='A')temp1=A;
        else if(poly1=='B')temp1=B;
        else if(poly1=='C')temp1=C;
        else if(poly1=='R')temp1=R;
        if(poly2=='A')temp2=A;
        else if(poly2=='B')temp2=B;
        else if(poly2=='C')temp2=C;
        else if(poly2=='R')temp2=R;
        int [][]temp=new int[temp1.size()+temp2.size()][2];
        for(int i=0;i<temp1.size();++i) {
            temp[i][0] = ((Point) temp1.get(i)).x;
            temp[i][1] = ((Point) temp1.get(i)).y;
        }
        for(int i=temp1.size();i<temp.length;++i){
            temp[i][0] = ((Point) temp2.get(i-temp1.size())).x;
            temp[i][1] = ((Point) temp2.get(i-temp1.size())).y;
        }
        java.util.Arrays.sort(temp, new java.util.Comparator<int[]>() {public int compare(int[] a, int[] b) {return a[1]-b[1];}});
        for (int i=0;i<temp1.size()+temp2.size()-1;++i){
            if(temp[i][1]==temp[i+1][1]){
                temp[i][0]+=temp[i+1][0];
                temp[i+1][0]=0;temp[i+1][1]=0;
            }
        }
        java.util.Arrays.sort(temp, new java.util.Comparator<int[]>() {public int compare(int[] a, int[] b) {return a[1]-b[1];}});
        for (int i=0;i<temp1.size()+temp2.size();++i){
            if(temp[i][0]!=0){
                Point p=new Point(temp[i][0],temp[i][1]);
                R.add(p);
            }
        }
        int[][]res=new int[R.size()][2];
        for(int i=0;i<R.size();++i){
            res[i][0]=((Point)R.get(i)).x;
            res[i][1]=((Point)R.get(i)).y;
        }
        return res ;
    }

    public int[][] subtract(char poly1, char poly2) {
        try {
            R.clear();
        }catch (Exception e){}
        Doubly_linked_list temp1=new Doubly_linked_list();
        Doubly_linked_list temp2=new Doubly_linked_list();
        if(poly1=='A')temp1=A;
        else if(poly1=='B')temp1=B;
        else if(poly1=='C')temp1=C;
        if(poly2=='A')temp2=A;
        else if(poly2=='B')temp2=B;
        else if(poly2=='C')temp2=C;
        int [][]temp=new int[temp1.size()+temp2.size()][2];
        for(int i=0;i<temp1.size();++i) {
            temp[i][0] = ((Point) temp1.get(i)).x;
            temp[i][1] = ((Point) temp1.get(i)).y;
        }
        for(int i=temp1.size();i<temp.length;++i){
            temp[i][0] = -((Point) temp2.get(i-temp1.size())).x;
            temp[i][1] = ((Point) temp2.get(i-temp1.size())).y;
        }
        java.util.Arrays.sort(temp, new java.util.Comparator<int[]>() {public int compare(int[] a, int[] b) {return Math.abs(a[1])-Math.abs(b[1]);}});
        for (int i=0;i<temp1.size()+temp2.size()-1;++i){
            if(Math.abs(temp[i][1])==Math.abs(temp[i+1][1])){
                temp[i][0]+=temp[i+1][0];
                temp[i+1][0]=0;temp[i+1][1]=0;
            }
        }
        java.util.Arrays.sort(temp, new java.util.Comparator<int[]>() {public int compare(int[] a, int[] b) {return a[1]-b[1];}});
        for (int i=0;i<temp1.size()+temp2.size();++i){
            if(temp[i][0]!=0){
                Point p=new Point(temp[i][0],temp[i][1]);
                R.add(p);
            }
        }
        int[][]res=new int[R.size()][2];
        for(int i=0;i<R.size();++i){
            res[i][0]=((Point)R.get(i)).x;
            res[i][1]=((Point)R.get(i)).y;
        }
        return res ;
    }
    
    public int[][] multiply(char poly1, char poly2) {
        try {
            R.clear();
        }catch (Exception e){}
        Doubly_linked_list temp1=new Doubly_linked_list();
        Doubly_linked_list temp2=new Doubly_linked_list();
        if(poly1=='A')temp1=A;
        else if(poly1=='B')temp1=B;
        else if(poly1=='C')temp1=C;
        if(poly2=='A')temp2=A;
        else if(poly2=='B')temp2=B;
        else if(poly2=='C')temp2=C;
        int[][]temp=new int[temp1.size()*temp2.size()][2];
        int counter=0;
        for(int i=0;i<temp1.size();++i){
            for(int j=0;j<temp2.size();++j){
                temp[counter][0]=((Point)temp1.get(i)).x*((Point)temp2.get(j)).x;
                temp[counter][1]=((Point)temp1.get(i)).y+((Point)temp2.get(j)).y;
                ++counter;
            }
        }

        java.util.Arrays.sort(temp, new java.util.Comparator<int[]>() {public int compare(int[] a, int[] b) {return a[1]-b[1];}});
        for (int i=0;i<temp1.size()*temp2.size()-1;++i){
            if(temp[i][1]==temp[i+1][1]){
                temp[i+1][0]+=temp[i][0];
                temp[i][0]=0;temp[i][1]=0;
            }
        }
        java.util.Arrays.sort(temp, new java.util.Comparator<int[]>() {public int compare(int[] a, int[] b) {return a[1]-b[1];}});

        for (int i=0;i<temp.length;++i){
            if(temp[i][0]!=0){
                Point p=new Point(temp[i][0],temp[i][1]);
                R.add(p);
            }
        }

        int[][]res=new int[R.size()][2];
        for(int i=0;i<R.size();++i){
            res[i][0]=((Point)R.get(i)).x;
            res[i][1]=((Point)R.get(i)).y;
        }
        return res ;
    }
    boolean is_empty(char input){
        if(input=='A')return A.isEmpty();
        else if(input=='B')return B.isEmpty();
        else if(input=='C')return C.isEmpty();
        else return R.isEmpty();
    }
}
