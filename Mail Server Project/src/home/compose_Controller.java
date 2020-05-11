package home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class compose_Controller {
    @FXML
    private int important_=0;
    public static Doubly_linked_list attachments=new Doubly_linked_list();
    @FXML
    private TextField Recipients;

    @FXML
    private TextField Subject;

    @FXML
    private TextArea body;

    @FXML
    private ChoiceBox<String> choose;

    @FXML
    ObservableList<String> attachs= FXCollections.observableArrayList();

    @FXML
    private Button Send;
    @FXML
    private Button discard;
    @FXML
    private Button btnAttach;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnClose;
    @FXML
    private ToggleButton important;
    @FXML
    void test(){
        attachs.clear();
        for(int i=0;i<attachments.size();++i){
            attachs.add((String) attachments.get(i));
        }
    }
    @FXML
    private void initialize (){
        choose.setItems(attachs);

    }
    @FXML
    void discard(ActionEvent event) {
        Stage stage = (Stage) discard.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Clsoe(ActionEvent event) {
        compose.compose_mail_draft(main_menu_Controller.path,Recipients.getText(),Subject.getText(),body.getText(),attachments,important_);
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Minimize(ActionEvent event) {

    }

    @FXML
    void btnSend(ActionEvent event) {
        if(compose.compose_mail_send(main_menu_Controller.path,Recipients.getText(),Subject.getText(),body.getText(),attachments,important_)) {
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void important(ActionEvent event) {
        if(important_==0)important_=1;
        else important_=0;
    }
    @FXML
    void eventbtn_attach(ActionEvent event) {
        FileChooser.fileChooser();
    }

}
