import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application {

    private static Stage stg;

    public static void main(String[] args) {
        System.out.println("Successfully Launched Application.");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

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
        Parent pane = FXMLLoader.load(getClass().getResource("src/" + fxmlFile));
        stg.getScene().setRoot(pane);
        System.out.println(fxmlFile + " File Loaded.");
    }

}
