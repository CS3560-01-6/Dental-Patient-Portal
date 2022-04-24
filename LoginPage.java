import java.beans.EventHandler;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPage {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField patientID;

    @FXML
    private Label wrongLogin;

    @FXML
    void patientLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    /* Checks the login using Patient ID and Password. */
     private void checkLogin() throws IOException {
         App app = new App();
        
         /* implementation for checking login */
         
         //if correct login, app.changeScene("homePage.fxml");
         //if any login fields are empty, wrongLogin.setText(MESSAGE);
         // else if username or password, wrong.Login.setText(MESSAGE);
    }

}


