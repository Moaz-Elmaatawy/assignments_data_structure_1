package home;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class compose {
    static boolean compose_mail_send(String from , String to , String sub, String body, Doubly_linked_list attachments, int important){
        if(to.length()==0||!to.contains("@")){
            JOptionPane.showMessageDialog(null,"Please enter correct e-mail !");
            return false;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date date = new Date();
        LocalDateTime date = LocalDateTime.now();

        String path= System.getProperty("user.dir");
        path=path+"\\"+"Mail_server_Data"+"\\"+"Accounts"+"\\"+from+"\\"+"Sent";
        File name_of_mail=new File(path+"\\"+"counter.txt");
        int name=0;
        try{
            Scanner scanner=new Scanner(name_of_mail);name=scanner.nextInt();scanner.close();}catch (Exception ignored){}
        try {
            FileWriter fr = new FileWriter(name_of_mail);
            fr.write(Integer.toString( name+1));
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(path+"/"+name+"_"+"sent").mkdir();
        path=path+"\\"+name+"_"+"sent";
        try {
            String path2=path+"\\"+"index.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file);
            fr.write(from+"\n"+to+"\n"+sub+"\n"+date+"\n"+important);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path2=path+"\\"+"body.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file);
            fr.write(body);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path2=path+"\\"+"attachments_names.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file);
            int n=attachments.size();
            while (n--!=0){
                String src_file=(String)attachments.get(n);
                File f1=new File(src_file);
                String[] split=src_file.split("\\\\");
                File f2= new File(path+"\\"+split[split.length-1]);
                fr.write(split[split.length-1]+"\n");
            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(path+"/"+"attachments").mkdir();
        path=path+"\\"+"attachments";
        int n=attachments.size();
        while (n--!=0){
            String src_file=(String)attachments.get(n);
            File f1=new File(src_file);
            String[] split=src_file.split("\\\\");
            File f2= new File(path+"\\"+split[split.length-1]);
            try{copy(f1,f2);}catch (Exception ignored){}
        }

        if (!SIGN.check_username(to)){
            path= System.getProperty("user.dir");
            path=path+"\\"+"Mail_server_Data"+"\\"+"Accounts"+"\\"+to+"\\"+"inbox";
            name_of_mail=new File(path+"\\"+"counter.txt");
            name=0;
            try{
                Scanner scanner=new Scanner(name_of_mail);name=scanner.nextInt();scanner.close();}catch (Exception ignored){}
            try {
                FileWriter fr = new FileWriter(name_of_mail);
                fr.write(Integer.toString( name+1));
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new File(path+"/"+name+"_inbox").mkdir();
            path=path+"\\"+name+"_inbox";
            try {
                String path2=path+"\\"+"index.txt";
                File file = new File(path2);
                FileWriter fr = new FileWriter(file);
                fr.write(from+"\n"+to+"\n"+sub+"\n"+date+"\n"+important);
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String path2=path+"\\"+"body.txt";
                File file = new File(path2);
                FileWriter fr = new FileWriter(file);
                fr.write(body);
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String path2=path+"\\"+"attachments_names.txt";
                File file = new File(path2);
                FileWriter fr = new FileWriter(file);
                n=attachments.size();
                while (n--!=0){
                    String src_file=(String)attachments.get(n);
                    File f1=new File(src_file);
                    String[] split=src_file.split("\\\\");
                    File f2= new File(path+"\\"+split[split.length-1]);
                    fr.write(split[split.length-1]+"\n");
                }

                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new File(path+"/"+"attachments").mkdir();
            path=path+"\\"+"attachments";
            n=attachments.size();
            while (n--!=0){
                String src_file=(String)attachments.get(n);
                File f1=new File(src_file);
                String[] split=src_file.split("\\\\");
                File f2= new File(path+"\\"+split[split.length-1]);
                try{copy(f1,f2);}catch (Exception ignored){}
            }
        }
        return true;
    }
    static boolean  compose_mail_draft(String from , String to , String sub, String body, Doubly_linked_list attachments, int important){
        if(to.length()==0||!to.contains("@")){
            //JOptionPane.showMessageDialog(null,"Please enter correct e-mail !");
            return false;
        }
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date date = new Date();
        LocalDateTime date = LocalDateTime.now();

        String path= System.getProperty("user.dir");
        path=path+"\\"+"Mail_server_Data"+"\\"+"Accounts"+"\\"+from+"\\"+"Drafts";
        File name_of_mail=new File(path+"\\"+"counter.txt");
        int name=0;
        try{
            Scanner scanner=new Scanner(name_of_mail);name=scanner.nextInt();scanner.close();}catch (Exception ignored){}
        try {
            FileWriter fr = new FileWriter(name_of_mail);
            fr.write(Integer.toString( name+1));
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(path+"/"+name+"_drafts").mkdir();
        path=path+"\\"+name+"_drafts";
        try {
            String path2=path+"\\"+"index.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file);
            fr.write(from+"\n"+to+"\n"+sub+"\n"+date+"\n"+important);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path2=path+"\\"+"body.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file);
            fr.write(body);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path2=path+"\\"+"attachments_names.txt";
            File file = new File(path2);
            FileWriter fr = new FileWriter(file);
            int n=attachments.size();
            while (n--!=0){
                String src_file=(String)attachments.get(n);
                File f1=new File(src_file);
                String[] split=src_file.split("\\\\");
                File f2= new File(path+"\\"+split[split.length-1]);
                fr.write(split[split.length-1]+"\n");
            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(path+"/"+"attachments").mkdir();
        path=path+"\\"+"attachments";
        int n=attachments.size();
        while (n--!=0){
            String src_file=(String)attachments.get(n);
            File f1=new File(src_file);
            String[] split=src_file.split("\\\\");
            File f2= new File(path+"\\"+split[split.length-1]);
            try{copy(f1,f2);}catch (Exception ignored){}
        }
        return true;
    }
    private static void copy(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }
}
