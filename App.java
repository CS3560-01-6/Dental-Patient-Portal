import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application {

    private static Stage stg;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        stg = primaryStage;
        primaryStage.setTitle("Cal Poly Pomona Dental Office");
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("src/LoginScene.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /* Changes the page to scene passed as the argument. */
    public void changeScene(String fxmlFile) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxmlFile));
        stg.getScene().setRoot(pane);
    }

}