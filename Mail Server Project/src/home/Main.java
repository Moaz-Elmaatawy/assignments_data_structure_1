package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private double xoffset;
    private double yoffset;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sign_in.fxml"));
        root.setOnMousePressed(event -> { xoffset=event.getSceneX();yoffset=event.getSceneY(); });
        root.setOnMouseDragged(e->{ primaryStage.setX(e.getScreenX()-xoffset);primaryStage.setY(e.getScreenY()-yoffset); });
        primaryStage.setTitle("COCO Mail");
        primaryStage.setScene(new Scene(root, 975, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
