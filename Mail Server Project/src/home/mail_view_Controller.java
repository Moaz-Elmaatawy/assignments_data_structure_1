package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class mail_view_Controller {

    @FXML
    private Button Back;

    @FXML
    private Label Email;
    @FXML
    private Label FROM;
    @FXML
    private Label Date;
    @FXML
    private Label Important;

    @FXML
    private Label Subject;

    @FXML
    private TextArea Message;

    @FXML
    private TextArea Attach;

    @FXML
    void back_action(ActionEvent event) {
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        String mail_path= main_menu_Controller.open.get(0).getdir();
        String body_txt="",sub="",attachment="Attachments:",from="",to="",date="",important="";
        File body=new File(mail_path+"\\body.txt");
        try {
            Scanner scanner = new Scanner(body);
            while(scanner.hasNextLine()){
                body_txt=body_txt+"\n"+scanner.nextLine();
            }
            scanner.close();
        }catch(Exception e){
            System.out.println("body");
        }
        Message.setText(body_txt);

        File index=new File(mail_path+"\\index.txt");
        try {
            Scanner scanner = new Scanner(index);
            from=scanner.nextLine();
            to=scanner.nextLine();
            sub=scanner.nextLine();
            date=scanner.nextLine();
            important=scanner.nextLine();
            scanner.close();
        }catch(Exception e){
            System.out.println("index");
        }
        Subject.setText(sub);
        if(main_menu_Controller.last_button.compareTo("inbox")==0){
            FROM.setText("From");
            Email.setText(from);}
        else {Email.setText(to);
            FROM.setText("To");}
        Date.setText(date);
        if(important.compareTo("0")==0)Important.setText("");
        else{ Important.setText("Important");}

        File attach=new File(mail_path+"\\attachments_names.txt");
        try {
            Scanner scanner = new Scanner(attach);
            while (scanner.hasNextLine()){
                attachment=attachment+"\n- "+scanner.nextLine();
            }
        }catch(Exception e){
            System.out.println("attachments");
        }
        Attach.setText(attachment);
    }

}
