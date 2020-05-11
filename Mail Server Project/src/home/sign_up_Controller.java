package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;

public class sign_up_Controller {
    public TextField nickname;
    public TextField Username;

    @FXML
    private PasswordField Password_1;

    @FXML
    private PasswordField Password_2;

    @FXML
    private Button SignUP;

    @FXML
    private TextField phone_number;

    @FXML
    private Button Login;

    @FXML
    private Button Cancel;

    private double xoffset;
    private double yoffset;

    @FXML
    void cancelButton(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    void eventButton_1(ActionEvent event) {
        if(nickname.getText().length()==0) JOptionPane.showMessageDialog(null,"Please enter your preferred nickname!");
        else if(Username.getText().length()==0) JOptionPane.showMessageDialog(null,"Please enter your preferred Username!");
        else if(phone_number.getText().length()==0) JOptionPane.showMessageDialog(null,"Please enter your number!");
        else if(Password_1.getText().length()==0) JOptionPane.showMessageDialog(null,"Please enter your password!");
        else if(Password_1.getText().compareTo(Password_2.getText())!=0){
            JOptionPane.showMessageDialog(null,"Please rewrite the password correctly !");
        }
        else if(Password_1.getText().length()<5) JOptionPane.showMessageDialog(null,"Please enter password of 5 character ot more!");
        else {
            if(SIGN.signUp(nickname.getText(), Username.getText(), Password_1.getText(), phone_number.getText())){
                JOptionPane.showMessageDialog(null,"Congratulations !! \nYour account has been created.\nYour mail is: "+Username.getText()+"@coco.com");
                try {
                    ((Node)event.getSource()).getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_in.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
                    root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(root1));
                    stage.show();

                } catch (Exception ignored) { }
            }
            else{
                JOptionPane.showMessageDialog(null,"This Username already exists !");
            }
        }
    }

    @FXML
    void eventButton_2(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_in.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
            root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ignored) { }
    }
}
