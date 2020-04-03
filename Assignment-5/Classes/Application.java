package eg.edu.alexu.csd.datastructure.stack;

/**
 * @author Moaz Elmaatawy
 * ID:18011824
 * this class for the conversion between infix notation to postfix notation
 * and the evaluation of postfix expression
 */

public class Application implements IExpressionEvaluator {
    /**
     * the method check if the input character is digit or space
     * @param c the character to be checked
     * @return true if it is digit or space
     */
    boolean is_digit(char c){
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == ' ';
    }

    /***
     * this methods puts the entered expression in correct format and check if it is a correct infix expression or not
     * @param s
     * @return the expression after putting in format
     * @exception RuntimeException if the expression if an invalid infix expression
     */
    String inform(String s){
        s=s.replaceAll(" ","");
        s=s.replaceAll("\t","");
        int n=s.length();
        if(n==0)throw new RuntimeException();
        //parentheses check////////////////////////////////////////////////////////////////////////
        Stack p=new Stack();
        for(int i=0;i<n;++i){
            if(s.charAt(i)=='(')
                p.push('(');
            else if (s.charAt(i)==')'&&!p.isEmpty())
                p.pop();
            else if(s.charAt(i)==')'&&p.isEmpty())
                throw new RuntimeException();
        }
        if(!p.isEmpty())throw new RuntimeException();
        /////////////dummy zero///////////////
        for(int i=0;i<s.length()-1;++i){
            if(isOperator(s.charAt(i))>0&&s.charAt(i+1)=='-'){
                s=s.substring(0,i+1)+"(0-"+s.substring(i+2);
                for (int j=i+4;j<s.length();++j){
                    if(j+1==s.length()){
                        s=s+')';
                        ++j;
                        i=j-1;
                    }
                    else if(isOperator(s.charAt(j))!=0){
                        s=s.substring(0,j)+")"+s.substring(j);
                        ++j;
                        i=j-1;
                        break;
                    }
                }
            }
            else if(isOperator(s.charAt(i))>0&&(s.charAt(i+1)=='/'||s.charAt(i+1)=='+'||s.charAt(i+1)=='*'))
                throw new RuntimeException();
        }
        if(s.charAt(0)=='-'){
            for(int i=1;i<s.length();++i){
                if(isOperator(s.charAt(i))>0){
                    s="(0-"+s.substring(1,i)+")"+s.substring(i);
                    break;
                }
                else if(i+1==s.length()){
                    s="(0-"+s.substring(1,i+1)+")";
                    break;
                }
            }
        }
        //System.out.println(s);
        //////////////////////////////number of operants and operators////////////////////////////////////////////////////
        int operators=0,numz=0;
        for(int i=0;i<s.length();++i) {
            if(i==0&&(Character.isDigit(s.charAt(0))||Character.isAlphabetic(s.charAt(0))))++numz;
            if(i!=0&&(Character.isDigit(s.charAt(i))||Character.isAlphabetic(s.charAt(i)))&&isOperator(s.charAt(i-1))!=0)++numz;
            if(isOperator(s.charAt(i))>0){
                ++operators;
            }
            else if (!Character.isDigit(s.charAt(i)) && !Character.isAlphabetic(s.charAt(i))&&isOperator(s.charAt(i))==0)
                throw new RuntimeException();
        }
        //System.out.println("ope = "+operators+ "n= "+numz);
        if(operators+1!=numz)throw new RuntimeException();

        return s ;
    }

    /***
     * to check if a character is an operator or not
     * @param o the input character
     * @return the precedence of the operator or 0
     * if it a parenthesis return -1
     */
    int isOperator(char o){
        if(o=='*'||o=='/')
            return 2;
        else if (o=='+'||o=='-')
            return 1;
        else if (o=='('||o==')')
            return -1;
        else
            return 0;
    }

    /***
     * @param expression infix expression
     * change the infix to postfix
     * @return the postfix expression
     * @exception RuntimeException if the entered infix expression was not correct
     */
    @Override
    public String infixToPostfix(String expression) {
        expression=inform(expression);
        //System.out.println(expression);
        int n=expression.length();
        char[]out=new char[2*n];int index=0;
        Stack operations =new Stack();
        for(int i=0;i<n;++i){
            //System.out.println(out);
            if(isOperator(expression.charAt(i))==0) {
                out[index] = expression.charAt(i);
                if(i+1==n)out[++index]=' ';
                if(i+1<n&&isOperator(expression.charAt(i+1))!=0)out[++index]=' ';
                ++index;
            }
            else{
                if(expression.charAt(i)=='(') {
                    operations.push(expression.charAt(i));
                }
                else if(expression.charAt(i)==')'){
                    while((char)operations.peek()!='('){
                        //System.out.println("-");
                        out[index] = (char) operations.pop();
                        out[++index]=' ';
                        ++index;
                    }
                    operations.pop();
                }
                else if(operations.isEmpty()|| isOperator(expression.charAt(i))>isOperator((char)operations.peek())){
                    operations.push(expression.charAt(i));
                    //System.out.println("--> "+expression.charAt(i));
                }
                else if(isOperator(expression.charAt(i))<=isOperator((char)operations.peek())){
                    //System.out.println("--><-- "+expression.charAt(i));
                    out[index]=(char)operations.pop();
                    out[++index]=' ';
                    ++index;
                    --i;
                }
            }
        }
        while(!operations.isEmpty()) {
            out[index] = (char) operations.pop();
            out[++index]=' ';
            ++index;
        }
        String res=new String(out);
        res=res.substring(0,index-1);
        return res;
    }

    /***
     * @param expression postfix expression
     * postfix expression
     * @return an int which is the number of evaluation
     */

    @Override
    public int evaluate(String expression) {
        expression=expression.replace("+"," + ");
        expression=expression.replace("-"," - ");
        expression=expression.replace("*"," * ");
        expression=expression.replace("/"," / ");
        expression=expression.replace("\t"," ");
        for(int i=0;i<expression.length()-1;++i){
            if(expression.charAt(i)==' '&&expression.charAt(i+1)==' ')
                expression=expression.replace("  "," ");
        }
        if(expression.charAt(0)==' ')
            expression=expression.substring(1,expression.length());
        if(expression.charAt(expression.length()-1)==' ')
            expression=expression.substring(0,expression.length()-1);
        //System.out.println(expression);
        if(expression.length()==0)throw new RuntimeException();

        for(int i=0;i<expression.length();++i){
            if((isOperator(expression.charAt(i))==0 ||isOperator(expression.charAt(i))==-1)&& !is_digit(expression.charAt(i)))
                throw new RuntimeException();
        }
        char[][] infix=new char[expression.length()][expression.length()];
        int row=0,col=0;
        for(int i=0;i<expression.length();++i){
            if(expression.charAt(i)==' ') {
                row=0;
                ++col;
            }else {
                infix[col][row]=expression.charAt(i);
                ++row;
            }
        }

        Stack s =new Stack();
        for(int i=0;i<=col;++i){
            if(isOperator(infix[i][0])!=0) {
                //System.out.println(infix[i]);
                if (s.size() < 2)
                    throw new RuntimeException();
                else {
                    double n1=(double)s.pop();
                    double n2=(double)s.pop();
                    s.push(answer(infix[i][0],n2,n1));
                }
            }
            else {
                s.push(Double.parseDouble(new String(infix[i])));
            }
        }
        //System.out.println("size= "+s.size());
        if(s.size()!=1)throw new RuntimeException();
        return (int)((double)s.peek());
    }

    /***
     * @param operator the kind of operation as a char
     * @param n1 the first operant
     * @param n2 the second operant
     * @return the output of the operation
     */
    double answer(char operator,double n1,double n2){
        if(operator=='+')return n1+n2;
        if(operator=='-')return n1-n2;
        if(operator=='*')return n1*n2;
        if(operator=='/')return n1/n2;
        return 0;
    }
}
