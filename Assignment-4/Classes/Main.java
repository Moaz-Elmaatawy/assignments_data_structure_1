package eg.edu.alexu.csd.datastructure.linkedList.cs;
//Ahmed Akram->18010056------Moaz Nabil->18011824
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.util.Scanner;

public class Main {
    static Application app =new Application();

    public static char valid_char(int a) {
        if(a==0) {
            System.out.println("Insert the variable name: A, B, C or R");
            Scanner scanner = new Scanner(System.in);
            String input;
            while (true) {
                input = scanner.nextLine();
                if (input.length() == 1 && (input.charAt(0) == 'A' || input.charAt(0) == 'B' || input.charAt(0) == 'C' || input.charAt(0) == 'R')) {
                    break;
                }
                System.out.println("Insert the variable name: A, B, C or R");
            }
            input.charAt(0);
            return input.charAt(0);
        }
        else{
            System.out.println("Insert the variable name: A, B or C");
            Scanner scanner = new Scanner(System.in);
            String input;
            while (true) {
                input = scanner.nextLine();
                if (input.length() == 1 && (input.charAt(0) == 'A' || input.charAt(0) == 'B' || input.charAt(0) == 'C' )) {
                    break;
                }
                System.out.println("Insert the variable name: A, B, C");
            }
            input.charAt(0);
            return input.charAt(0);

        }

    }

    static double  valid_double(){

        double input=0;
        while(true) {
            Scanner scanner=new Scanner(System.in);
            try {
                input = scanner.nextDouble();
            } catch (RuntimeException e) {
                System.out.println("Enter valid number");
                scanner.reset();
                continue;
            }
            break;
        }
        return input;

    }

    static int  valid_number(){

        int input=0;
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

    static void case_1(){
        Scanner scanner=new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Insert the variable name: A, B or C");
            input = scanner.nextLine();
            if(input.length()==1 && (input.charAt(0)=='A'||input.charAt(0)=='B'||input.charAt(0)=='C')){
                break;
            }
        }
        System.out.println("First insert the number of terms of the polynomial.");
        int terms=valid_number();
        System.out.println("Insert the polynomial terms in the form:\n" +
                "coeff1\nexponent1\ncoeff2\nexponent2\n ...");
        int[][] poly=new int [terms][2];
        for(int i=0;i<terms;++i){
            poly[i][0]=valid_number();
            poly[i][1]=valid_number();
        }
        if(input.equals("A")) app.setPolynomial('A',poly);
        else if(input.equals("B"))app.setPolynomial('B',poly);
        else if(input.equals("C"))app.setPolynomial('C',poly);
        System.out.println("Polynomial "+input+" is set");

    }
    static int  valid_input(){
        Scanner scanner=new Scanner(System.in);
        while (true) {
            int n = valid_number();
            if (n >= 0 && n < 8)
                return n;
            System.out.println("please enter number between 0-7");
        }
    }
    static void case_2(){
        char temp=valid_char(0);

        if(app.is_empty(temp)) System.out.println(temp+" is empty!");
        else System.out.println(app.print(temp));
    }

    static void case_3(){
        char temp1;
        temp1=valid_char(1);if(app.is_empty(temp1)) {System.out.println(temp1+" is empty!!");return;}
        char temp2;
        temp2=valid_char(1);if(app.is_empty(temp2)) {System.out.println(temp2+" is empty!!");return;}
        System.out.println(app.print(temp1));
        System.out.println(app.print(temp2));
        app.add(temp1,temp2);
        System.out.println(app.print('R'));
    }

    static void case_4(){
        char temp1;
        temp1=valid_char(1);if(app.is_empty(temp1)){ System.out.println(temp1+" is empty!!");return;}
        char temp2;
        temp2=valid_char(1);if(app.is_empty(temp2)) {System.out.println(temp2+" is empty!!");return;}
        System.out.println(app.print(temp1));
        System.out.println(app.print(temp2));
        app.subtract(temp1,temp2);
        System.out.println(app.print('R'));
    }

    static void case_5(){
        char temp1;
        temp1=valid_char(1);if(app.is_empty(temp1)){ System.out.println(temp1+" is empty!!");return;}
        char temp2;
        temp2=valid_char(1);if(app.is_empty(temp2)) {System.out.println(temp2+" is empty!!");return;}
        System.out.println(app.print(temp1));
        System.out.println(app.print(temp2));
        app.multiply(temp1,temp2);
        System.out.println(app.print('R'));
    }

    static void case_6(){
        char temp=valid_char(1);
        if(app.is_empty(temp)) {System.out.println(temp+" is empty!!");return;}
        System.out.println(app.print(temp));
        System.out.println("Enter a number to evaluate at");
        double num=valid_double();
        System.out.println(app.evaluatePolynomial(temp,(float) num));
    }
    static void case_7(){
        System.out.println("Insert the variable name: A, B or C");
        Scanner scanner=new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if(input.length()==1 && (input.charAt(0)=='A'||input.charAt(0)=='B'||input.charAt(0)=='C'||input.charAt(0)=='R')){
                break;
            }
            System.out.println("Insert the variable name: A, B or C");
        }
        app.clearPolynomial(input.charAt(0));
        System.out.println(input.charAt(0)+" is empty now!");
    }

    public static void main(String [] args){

        while(true){
            System.out.println();
            System.out.println("Please choose an action\n-----------------------");
            System.out.println("1- Set a polynomial variable\n" +
                    "2- Print the value of a polynomial variable\n" +
                    "3- Add two polynomials\n" +
                    "4- Subtract two polynomials\n" +
                    "5- Multiply two polynomials\n"+"6- Evaluate a polynomial at some point\n" +
                    "7- Clear a polynomial variable\n" +
                    "====================================================================");
                    int input=valid_input();
                    switch (input){
                        case 1:
                            case_1();
                            break;
                        case 2:
                            case_2();
                            break;
                        case 3:
                            case_3();
                            break;
                        case 4:
                            case_4();
                            break;
                        case 5:
                            case_5();
                            break;
                        case 6:
                            case_6();
                            break;
                        default:
                            case_7();
                            break;
                    }
        }
    }
}
