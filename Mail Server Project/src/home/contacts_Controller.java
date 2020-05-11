package home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class contacts_Controller implements Initializable {
    @FXML
    private TableView<Contact> table_view;
    @FXML
    private TableColumn<Contact, String> Name;
    @FXML
    private TableColumn<Contact, String> Mobile;
    @FXML
    private TableColumn<Contact, String> Email;

    @FXML
    private Button add;

    @FXML
    private Button back;

    @FXML
    private TextField Search;

    @FXML
    private Button delete;
    private double xoffset;
    private double yoffset;

    @FXML
    void add_action(ActionEvent event) {
        try {
            //((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_contact.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
            root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ignored) { }
    }

    @FXML
    void back_action(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @FXML
    void delete_action(ActionEvent event) {
        ObservableList<Contact> del;
        del=table_view.getSelectionModel().getSelectedItems();
        Doubly_linked_list to_remove=new Doubly_linked_list();
        for(int j=0;j<del.size();j++) {
            String remove = del.get(j).getName() + "%" + del.get(j).getMob() + "%" + del.get(j).getMail();
            to_remove.add(remove);
        }
        obs.clear();
        Doubly_linked_list temp = new Doubly_linked_list();
        String path = System.getProperty("user.dir") + "\\" + "Mail_server_Data" + "\\" + "Accounts" + "\\" + main_menu_Controller.path + "\\" + "contacts.txt";
        File contacts = new File(path);
        try {
            Scanner scanner = new Scanner(contacts);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                if (!to_remove.contains(str)) {
                    temp.add(str);
                    String[] data = str.split("%");
                    obs.add(new Contact(data[0], data[1], data[2]));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "an error occurred try again !!");
        }
        try {
            File file = new File(path);
            FileWriter fr = new FileWriter(file);
            for (int i = 0; i < temp.size(); ++i) {
                fr.write((String) temp.get(i) + "\n");
            }
            fr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "an error occurred try again !!");
        }
        table_view.setItems(obs);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Mobile.setCellValueFactory(new PropertyValueFactory<>("mob"));
        Email.setCellValueFactory(new PropertyValueFactory<>("mail"));
        obs.clear();
        String path= System.getProperty("user.dir")+"\\"+"Mail_server_Data"+"\\"+"Accounts"+"\\"+ main_menu_Controller.path+"\\"+"contacts.txt";
        File contacts=new File(path);

        try {
            Scanner scanner = new Scanner(contacts);
            while(scanner.hasNext()){
                String str=scanner.nextLine();
                String[] data=str.split("%");
                obs.add(new Contact(data[0],data[1],data[2]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            //JOptionPane.showMessageDialog(null,"an error occurred try again !!");
        }
        table_view.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        table_view.setItems(obs);
    }
    public static ObservableList<Contact> obs = FXCollections.observableArrayList();

    @FXML
    void  search(ActionEvent event){
        FilteredList<Contact> filteredData = new FilteredList<>(obs, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Contact -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (Contact.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (Contact.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (Contact.getMob().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Contact> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table_view.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table_view.setItems(sortedData);
    }

}
