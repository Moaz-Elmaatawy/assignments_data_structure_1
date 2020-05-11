package home;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SIGN {
    public static boolean signIn(String mail, String password) {
        String path= System.getProperty("user.dir");
        new File(path+"/"+"Mail_server_Data").mkdir();
        path=path+"\\"+"Mail_server_Data";
        //new  File(path+"/"+"Accounts").mkdir();
        //path=path+"\\"+"Accounts";
        try {
            File myObj = new File(path+"\\accounts.txt");
            myObj.createNewFile();
        } catch (IOException ignored) {}
        path=path+"\\"+"accounts.txt";
        File accounts= new File(path);
        try {
            Scanner scanner = new Scanner(accounts);
            while(scanner.hasNext()){
                String data=scanner.nextLine();
                String[] user_pass=data.split("%");
                if(user_pass[0].compareTo(mail)==0&&user_pass[1].compareTo(password)==0){
                    return true;
                }
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        return  false;
    }
    static boolean check_username(String mail){
        String path= System.getProperty("user.dir");
        new File(path+"/"+"Mail_server_Data").mkdir();
        path=path+"\\"+"Mail_server_Data";
        try {
            File f=new File(path+"\\accounts.txt");
            Scanner sc=new Scanner(f);
            while (sc.hasNext()){
                String mails=(sc.nextLine().split("%"))[0];
                if(mail.compareTo(mails)==0) {
                    sc.close();
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public static boolean signUp(String name, String username , String password, String number){
        String mail=username+"@coco.com";
        if (!check_username(mail)){
            return false;
        }
        //password="123456";

        String path= System.getProperty("user.dir");
        new File(path+"/"+"Mail_server_Data").mkdir();
        path=path+"\\"+"Mail_server_Data";
        try {
            String path2=path+"\\"+"accounts.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file, true);
            fr.write(mail+"%"+password+"\n");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new File(path+"/"+"Accounts").mkdir();
        path=path+"\\"+"Accounts";
        new File(path+"/"+mail).mkdir();
        path=path+"\\"+mail;

        new File(path+"/"+"inbox").mkdir();
        new File(path+"/"+"Drafts").mkdir();
        new File(path+"/"+"Trash").mkdir();
        new File(path+"/"+"Sent").mkdir();
        try {
            String path2=path+"\\"+"info.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file, true);
            fr.write(name+"\n"+username+"\n"+password+"\n"+number);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         File contacts = new File(path+"\\"+"contacts.txt");
        try {
            String path2=path+"\\"+"Sent"+"\\"+"counter.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file);
            fr.write("0");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path2=path+"\\"+"inbox"+"\\"+"counter.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file);
            fr.write("0");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String path2=path+"\\"+"Drafts"+"\\"+"counter.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file);
            fr.write("0");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
