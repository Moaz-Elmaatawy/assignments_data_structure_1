package home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class main_menu_Controller implements Initializable {
    public static String path;
    public  static String last_button;
    @FXML
    private ImageView ProfilePicture;

    @FXML
    private Label NameOfUser;

    @FXML
    private Button btnInbox;
    @FXML
    private Button sent;
    @FXML
    private Button delete;
    @FXML
    private Button refresh;

    @FXML
    private Button btnNewEmail;

    @FXML
    private Button btnContacts;

    @FXML
    private Button btnFilters;

    @FXML
    private Button btnTrash;

    @FXML
    private Button btnDetails;

    @FXML
    private Button btnSignOut;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField Search;

    @FXML
    private Label TotalMessages_Counter;

    @FXML
    private Text State;

    @FXML
    private Label TotalDelivered_Counter;

    @FXML
    private Label TotalSent_Counter;

    @FXML
    private Label Trash_Counter;

    @FXML
    private VBox pnItems;

    @FXML
    private javafx.scene.control.TableView<Mail> TableView;

    @FXML
    private TableColumn<Mail, String> NameOfSender;

    @FXML
    private TableColumn<Mail, String> Subject;

    @FXML
    private TableColumn<Mail, String> DeliveryDate;

    @FXML
    private TableColumn<Mail, String> Important;

    private double xoffset;
    private double yoffset;

    @FXML
    private Button CancelButton;

    @FXML
    void Close(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    void btnEvent_Contacts(ActionEvent event) {
        try {
            //((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contacts.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
            root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ignored) { }
        refresh();
    }

    @FXML
    void btnEvent_Details(ActionEvent event) {
        try {
            //((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("details.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
            root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ignored) { }
        refresh();
    }
    @FXML
    void Sent(ActionEvent event){
        State.setText("Sent");
        NameOfSender.setText("To");
        last_button="Sent";
        view_data("Sent");
        refresh();
    }
    public static ObservableList<Mail> open;
    @FXML
    void Open (ActionEvent event){
        open=TableView.getSelectionModel().getSelectedItems();
        if(open.size()==1) {
            try {
                //((Node)event.getSource()).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mail_view.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
                root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (Exception ignored) {
            }
        }
        else JOptionPane.showMessageDialog(null,"Please select one mail to open !!");
        refresh();
    }
    void deleteDirectory(File file) throws IOException {
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectory(entry);
                }
            }
        }
        if (!file.delete()) {
            JOptionPane.showMessageDialog(null, "an error occurred try again !!");
        }
    }
    @FXML
    void Delete(ActionEvent event)  {
        if(last_button.compareTo("Trash")==0){
            ObservableList<Mail> del;
            del=TableView.getSelectionModel().getSelectedItems();
            for(int i=0;i<del.size();++i) {
                String remove = del.get(i).getdir();
                try{deleteDirectory(new File(remove));}catch (Exception e){
                    JOptionPane.showMessageDialog(null, "an error occurred try again !!");
                }
            }
            view_data(last_button);
        }
        else {
            String delete_path = System.getProperty("user.dir") + "\\" + "Mail_server_Data" + "\\" + "Accounts" + "\\" + path + "\\" + "Trash";
            ObservableList<Mail> del;
            del = TableView.getSelectionModel().getSelectedItems();
            for (int i = 0; i < del.size(); ++i) {
                String remove = del.get(i).getdir();
                Path source = Paths.get(remove);
                Path newdir = Paths.get(delete_path);
                try {
                    Files.move(source, newdir.resolve(source.getFileName()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "an error occurred try again !!");
                }
            }
            view_data(last_button);
        }
        refresh();
    }
    void refresh(){
        view_data("Sent");
        view_data("inbox");
        view_data("Trash");
        view_data("Drafts");
        view_data(last_button);
    }
    @FXML
    void Refresh(ActionEvent event){
        refresh();
    }

    @FXML
    void btnEvent_Filters(ActionEvent event) {
        State.setText("Drafts");
        NameOfSender.setText("To");
        last_button="Drafts";
        view_data("Drafts");
        refresh();
    }
    @FXML
    private void initialize (){
        NameOfUser.setText(path);
    }
    @FXML
    void btnEvent_Inbox(ActionEvent event) {
        State.setText("INBOX");
        NameOfSender.setText("From");
        last_button="inbox";
        view_data("inbox");
        refresh();
    }

    @FXML
    void btnEvent_NewEmail(ActionEvent event) {
        try {
            //((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("compose.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
            root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception ignored) { }
        refresh();
    }

    @FXML
    void btnEvent_Trash(ActionEvent event) {
        State.setText("Trash");
        NameOfSender.setText("To");
        last_button="Trash";
        view_data("Trash");
        refresh();
    }


    public static ObservableList<Mail> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        last_button="inbox";
        refresh();
    }
    public void view_data(String field){
        NameOfSender.setCellValueFactory(new PropertyValueFactory<>("name"));
        Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        DeliveryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        Important.setCellValueFactory(new PropertyValueFactory<>("imp"));
        int from;
        AtomicInteger count= new AtomicInteger();
        if(field.compareTo("inbox")==0)from=0;
        else from=1;
        data.clear();
        String dirName= System.getProperty("user.dir")+"\\"+"Mail_server_Data"+"\\"+"Accounts"+"\\"+path+"\\"+field;
        try {
            Files.list(new File(dirName).toPath())
                    .limit(10000)
                    .forEach(path -> {
                        if(String.valueOf(path).split("\\\\")[String.valueOf(path).split("\\\\").length-1].compareTo("counter.txt")!=0) {
                            count.incrementAndGet();
                            String path2=path+ "\\" + "index.txt";
                            File index = new File(path2);
                            try {
                                Scanner scanner = new Scanner(index);
                                String[]val=new String[5];
                                int i=0;
                                while (scanner.hasNext()) {
                                    val[i] = scanner.nextLine();
                                    ++i;
                                }
                                data.add(new Mail(val[from],val[2],val[3],val[4], String.valueOf(path)));
                                scanner.close();
                            } catch (FileNotFoundException e) {
                                JOptionPane.showMessageDialog(null, "an error occurred try again !!");
                            }
                        }
                    });
        }catch (Exception ignored){}
        TableView.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        if(field.compareTo("inbox")==0)TotalDelivered_Counter.setText(String.valueOf(count));
        if(field.compareTo("Sent")==0)TotalSent_Counter.setText(String.valueOf(count));
        if(field.compareTo("Trash")==0)Trash_Counter.setText(String.valueOf(count));
        TotalMessages_Counter.setText(String.valueOf(Integer.parseInt(TotalDelivered_Counter.getText())+ Integer.parseInt(TotalSent_Counter.getText())));

        TableView.setItems(data);

    }
    @FXML
    void btnEvent_SignOut(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_in.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
            root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"an error occurred try again !!");
        }
    }

    @FXML
    void  search(ActionEvent event){
        System.out.println("55555555555");
        FilteredList<Mail> filteredData = new FilteredList<>(data, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Mail -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (Mail.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (Mail.getSubject().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Mail> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(TableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        TableView.setItems(sortedData);
    }
}
