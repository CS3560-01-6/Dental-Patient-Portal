import java.beans.EventHandler;
import java.io.IOException;
import java.sql.*;

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

    Connection connection = null;

    @FXML
    void patientLogin(ActionEvent event) throws Exception {
        checkLogin();
    }

    /* Checks the login using Patient ID and Password. */
     private void checkLogin() throws Exception {
        App app = new App();
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();

        String verifyLoginQuery = "SELECT count(1) FROM patient WHERE patientID = '" + patientID.getText() + "' AND password = '" + password.getText() + "'";

        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(verifyLoginQuery);

        while(queryResult.next()) {
            if(queryResult.getInt(1) == 1) {
                System.out.println("Patient logged in successfully.");
                app.changeScene("HomeScene.fxml");
            } else if (patientID.getText().isEmpty() || password.getText().isEmpty()) {
                wrongLogin.setText("Missing required login fields.");
            } else {
                wrongLogin.setText("Invalid Patient ID or Password.");
            }
        }
    }

}


