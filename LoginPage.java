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
        checkLogin(); // calls method to verify login when user clicks log in button
    }

    /* Checks the login using Patient ID and Password. */
     private void checkLogin() {
        try
        {
            App app = new App();
            Handler sqlConnection = new Handler();
            connection = sqlConnection.connectDB(); // establishes connection to database

            String verifyLoginQuery = "SELECT count(1) FROM patient WHERE patientID = '" + patientID.getText() + "' AND password = '" + password.getText() + "'"; // query that returns 1 if there is a matching patient id and password or 0 if not found when executed

            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLoginQuery); // executes query and returns results from search

            while(queryResult.next()) { 
                if(queryResult.getInt(1) == 1) { // match is found
                    System.out.println("Patient logged in successfully.");
                    Patient patient = new Patient(Integer.parseInt(patientID.getText()));
                    app.loadHome(patient); // brings user to home page
                } else if (patientID.getText().isEmpty() || password.getText().isEmpty()) { // patient id and password fields are empty
                    wrongLogin.setText("Missing required login fields.");
                } else {
                    wrongLogin.setText("Invalid Patient ID or Password."); // patient id and password combo is not found in database
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error. Login Failed. " + e.getMessage());
        }
    }
}

