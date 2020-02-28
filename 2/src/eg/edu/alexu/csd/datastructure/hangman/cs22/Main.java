package eg.edu.alexu.csd.datastructure.hangman.cs22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static String[] getfile() throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int i=0;
        while(scanner.hasNext()){scanner.nextLine();++i;}
        String[] words =new String [i];
        scanner.close();
        scanner = new Scanner(file);
        for(int j=0;j<i;++j){
            words[j]=scanner.nextLine();
        }
        return words;
    }
    public static void main(String []args) throws Exception {
        String[] words= getfile();
        Hangman hang=new Hangman(10);
        hang.setDictionary(words);
        String secretword=hang.selectRandomSecretWord();

        int word_len=secretword.length();
        int towin=word_len;
        char[]ans=new char[word_len];
        for(int i=0;i<word_len;++i) {
            ans[i]='-';
        }
        Scanner scanner =new Scanner(System.in);

        System.out.println(secretword);
        System.out.println(ans);
        
        String user_inputs="";
        while(Hangman.max_guess>0){
            hang.ans=ans;
            System.out.println("you have "+Hangman.max_guess+" guesses left");
            System.out.print("you are guessing ");
            System.out.println(ans);
            String chara;
            do{chara = scanner.nextLine();}while (chara.length()!=1);
            char letter=chara.charAt(0);

            if(user_inputs.indexOf(letter)==-1) {user_inputs=user_inputs.concat(String.valueOf(letter));}
            else {System.out.println("you have enterd this letter before");continue;}
            String str=hang.guess(letter);
            ans=str.toCharArray();
            towin=hang.towin;
            if(towin==0){
                System.out.println("congrats you win !!! \n the word is "+secretword);
                break;
            }
            System.out.println();
        }
    }
}
