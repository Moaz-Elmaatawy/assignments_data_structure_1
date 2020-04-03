package eg.edu.alexu.csd.datastructure.stack;

import java.util.EmptyStackException;
import java.util.Scanner;

/***
 * @author Moaz Nabil Elmaatawy
 * ID:18011824
 */
public class Main {
    private static Stack s=new Stack();

    /**
     * the method get a valid number of the user
     * @exception RuntimeException if the user entered invalid input
     * @return the valid number
     */
    static int  valid_number(){
        int input;
        while(true) {
            Scanner scanner=new Scanner(System.in);
            try{

                input = scanner.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Enter valid number");
                scanner.reset();
                continue;
            }
            break;
        }
        return input;
    }

    /***
     * check if the input that user entered between 1 and 6
     * @return the number
     */
    static int  valid_input(){
        while (true) {
            int n = valid_number();
            if (n >= 1 && n < 7)
                return n;
            System.out.println("please enter number between 1-6");
        }
    }

    /***
     * The main
     * tests the stack implementation for push ,pop ,peek ,size and is empty
     * @param args
     */

    public static void main(String[]args){
        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.println("1: Push\n" +
                    "2: Pop\n" +
                    "3: Peek\n" +
                    "4: Get size\n" +
                    "5: Check if empty\n"+"6: Exist");
            int input = valid_input();
            switch (input) {
                case 1:
                    String in =scanner.nextLine();
                    s.push(in);
                    break;
                case 2:
                    try{s.pop();}
                    catch (EmptyStackException e){
                        System.out.println("Empty Stack");
                    }
                    break;
                case 3:
                    try{
                        System.out.println(s.peek());
                    }catch (EmptyStackException e) {
                        System.out.println("Empty Stack");
                    }
                    break;
                case 4:
                    System.out.println("Size : "+s.size());
                    break;
                case 5:
                    System.out.println(s.isEmpty());
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }
}
