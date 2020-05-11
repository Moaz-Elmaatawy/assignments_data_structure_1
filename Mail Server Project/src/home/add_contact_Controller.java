package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class add_contact_Controller {

    @FXML
    private Button close;

    @FXML
    private Button Add;

    @FXML
    private TextField Name;

    @FXML
    private TextField Mobile;

    @FXML
    private TextField Email;

    @FXML
    void Add_action(ActionEvent event) {
        if(Name.getText().length()==0||Mobile.getText().length()==0||Email.getText().length()==0){
            JOptionPane.showMessageDialog(null, "please fill all required!!");
        }
        else {
            String path = System.getProperty("user.dir") + "\\" + "Mail_server_Data" + "\\" + "Accounts" + "\\" + main_menu_Controller.path + "\\" + "contacts.txt";
            try {
                File file = new File(path);
                FileWriter fr = new FileWriter(file, true);
                fr.write(Name.getText() + "%" + Mobile.getText() + "%" + Email.getText() + "\n");
                fr.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "an error occurred try again !!");
            }

            contacts_Controller.obs.clear();
            String contact_path = System.getProperty("user.dir") + "\\" + "Mail_server_Data" + "\\" + "Accounts" + "\\" + main_menu_Controller.path + "\\" + "contacts.txt";
            File contacts = new File(contact_path);

            try {
                Scanner scanner = new Scanner(contacts);
                while (scanner.hasNext()) {
                    String str = scanner.nextLine();
                    String[] data = str.split("%");
                    contacts_Controller.obs.add(new Contact(data[0], data[1], data[2]));
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Stage stage = (Stage) Add.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void close_action(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
