package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class details_Controller {

    @FXML
    private Button back;

    @FXML
    private Text name;

    @FXML
    private Text email;

    @FXML
    private Text mobile;

    @FXML
    private Text password;

    @FXML
    void back_action(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
    @FXML
    void setting_details(){
        String path= System.getProperty("user.dir")+"\\"+"Mail_server_Data"+"\\"+"Accounts"+"\\"+main_menu_Controller.path+"\\"+"info.txt";
        try {
            File info = new File(path);
            Scanner scanner=new Scanner(info);
            name.setText(scanner.nextLine());
            email.setText(scanner.nextLine()+"@coco.com");
            password.setText(scanner.nextLine());
            mobile.setText(scanner.nextLine());
            scanner.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"an error occurred try again !!");
        }
    }

}
