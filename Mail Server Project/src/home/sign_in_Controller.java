package home;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;

public class sign_in_Controller {
    @FXML
    private TextField UserName;

    @FXML
    private PasswordField Password;

    private double xoffset;

    private double yoffset;

    @FXML
    void cancelButton(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    void eventbutton_1(ActionEvent event) {
        if(UserName.getText().length()==0) JOptionPane.showMessageDialog(null,"please enter your mail !");
        else if(Password.getText().length()==0) JOptionPane.showMessageDialog(null,"please enter your password !");
        else {
            if (!SIGN.signIn(UserName.getText(), Password.getText())) {
                JOptionPane.showMessageDialog(null, "Wrong E-mail or Password");
            } else {
                try {
                    main_menu_Controller.path=UserName.getText();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
                    root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(root1));
                    stage.show();

                } catch ( Exception ignored ) {
                    JOptionPane.showMessageDialog(null,"an error occurred try again !!");
                }
            }
        }

    }

    @FXML
    void eventbutton_2(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_up.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
            root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ignored) {}

    }
}
