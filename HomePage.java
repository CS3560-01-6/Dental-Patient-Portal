import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePage {

    @FXML
    private Text addressLine1;

    @FXML
    private Text addressLine2;

    @FXML
    private Text city;

    @FXML
    private Text dateOfBirth;

    @FXML
    private Text email;

    @FXML
    private Text firstName;

    @FXML
    private Tab invoicesTab;

    @FXML
    private Text lastFourDigits;

    @FXML
    private Text lastName;

    @FXML
    private Button logoutButton;

    @FXML
    private Text patientID;

    @FXML
    private Tab paymentHistoryTab;

    @FXML
    private Text phoneNumber;

    @FXML
    private Tab profileTab;

    @FXML
    private Text state;

    @FXML
    private Hyperlink updateProfileButton;

    @FXML
    private Text zip;

    Connection connection = null;

    /* Logs user out and returns back to login page */
    @FXML
    void logout(ActionEvent event) throws IOException {
        App app = new App();
        app.changeScene("LoginScene.fxml");
        System.out.println("Patient logged out successfully.");
    }

    /* Prompts the Update Profile Page. */
    @FXML
    void updateProfile(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("src/UpdateProfileScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Update Profile");
        stage.setScene(scene);
        stage.show();
        System.out.println("Update Profile popup launched successfully.");
    }

    public void displayPatientInfo(String patientID) throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();

        String getPatientInfo = "SELECT * FROM patient WHERE patientID = '" + patientID + "'";
        String getAddressInfo = "SELECT * FROM address WHERE patientID = '" + patientID + "'";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getPatientInfo);

        result.next();
        this.patientID.setText(patientID);
        this.firstName.setText(result.getString("fname"));
        this.lastName.setText(result.getString("lname"));
        this.dateOfBirth.setText(result.getString("dateofbirth"));
        this.email.setText(result.getString("email"));
        this.phoneNumber.setText(result.getString("phonenumber"));
    }
}
