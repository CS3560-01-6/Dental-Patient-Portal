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
        
        
        if(patientID.getText().toString().equals("123") && password.getText().toString().equals("abc")) { // CHANGE COMPARISON CONDITIONS TO DATABASE
            System.out.println("Patient logged in successfully.");
            app.changeScene("HomeScene.fxml");
        } else if (patientID.getText().isEmpty() || password.getText().isEmpty()) {
            wrongLogin.setText("Missing required login fields.");
        } else {
            wrongLogin.setText("Invalid Patient ID or Password.");
        }
    }

}


