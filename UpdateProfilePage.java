import java.sql.*;
import java.util.EventListener;

import com.mysql.cj.xdevapi.Result;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateProfilePage {

    @FXML
    private TextField CVVInput;

    @FXML
    private TextField addressLine1Input;

    @FXML
    private TextField addressLine2Input;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cardHolderInput;

    @FXML
    private TextField cardNumberInput;

    @FXML
    private TextField cityInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField expiryDateInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField phoneNumberInput;

    @FXML
    private Button saveButton;

    @FXML
    private TextField stateInput;

    @FXML
    private TextField zipInput;

    Patient patient;
    Address address;
    Connection connection = null;

    /* Closes Update Profile Page window and does not save any changes if made. */
    @FXML
    void cancelProfileChanges(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Are you sure you want to cancel before saving changes?");
        alert.setContentText("All unsaved changes will be lost.");
        if(alert.showAndWait().get() == ButtonType.OK) {
            Node node = (Node) event.getSource();
            Stage updateProfileStage = (Stage) node.getScene().getWindow();
            updateProfileStage.close();
            System.out.println("Update Profile Page Closed.");
        }
    }

    /* Saves any changes made to profile and updates attributes of Patient to new changes in database. */
    @FXML
    void saveProfile(ActionEvent event) throws Exception {

        patient.setFirstName(firstNameInput.getText());
        patient.setLastName(lastNameInput.getText());
        patient.setPhoneNumber(phoneNumberInput.getText());
        patient.setEmail(emailInput.getText());
        address.setAddressLine1(addressLine1Input.getText());
        address.setAddressLine2(addressLine2Input.getText());
        address.setCity(cityInput.getText());
        address.setState(stateInput.getText());
        address.setZip(Integer.parseInt(zipInput.getText()));

        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB(); 

        // query to update patient values
        String savePatient = "UPDATE patient SET fname = '" + patient.getFirstName() + "', lname = '" + patient.getLastName() + "', email = '" + patient.getEmail() + 
                            "', phonenumber = '" + patient.getPhoneNumber() + "' WHERE patientID = '" + patient.getPatientID() + "'"; 
        
        // query to update patient address values
        String saveAddress = "UPDATE address SET addressLine1 = '" + address.getAddressLine1() + "', addressLine2 = '" + address.getAddressLine2() + "', city = '" + address.getCity() + 
                            "', state = '" + address.getState() + "', zip = '" + address.getZip() + "' WHERE patientID = '" + patient.getPatientID() + "'"; 

        Statement statement = connection.createStatement();
        statement.executeUpdate(savePatient);
        statement.executeUpdate(saveAddress);

        App app = new App();
        app.loadHome(patient); // Updates home screen to new values of patient if changes were made

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Changes");
        alert.setHeaderText("Profile Updated Successfully.");
        alert.show();
        System.out.println("User Updated Profile.");
    }

    public void loadPatientInfo(Patient patient, Address address) {
        this.patient = patient;
        this.address = address;
        firstNameInput.setText(patient.getFirstName());
        lastNameInput.setText(patient.getLastName());
        phoneNumberInput.setText(patient.getPhoneNumber());
        emailInput.setText(patient.getEmail());
        addressLine1Input.setText(address.getAddressLine1());
        addressLine2Input.setText(address.getAddressLine2());
        cityInput.setText(address.getCity());
        stateInput.setText(address.getState());
        zipInput.setText(Integer.toString(address.getZip()));
    }

}
