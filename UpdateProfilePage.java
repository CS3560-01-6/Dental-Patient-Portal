import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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

    @FXML
    private Text errorText;

    Patient patient;
    Address address;
    PaymentInformation paymentInfo;
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
        errorText.setText("");

        // updates patient object
        patient.setFirstName(firstNameInput.getText());
        patient.setLastName(lastNameInput.getText());
        patient.setPhoneNumber(phoneNumberInput.getText());
        patient.setEmail(emailInput.getText());

        // updates address object
        address.setAddressLine1(addressLine1Input.getText());
        address.setAddressLine2(addressLine2Input.getText());
        address.setCity(cityInput.getText());
        address.setState(stateInput.getText());
        address.setZip(Integer.parseInt(zipInput.getText()));
        patient.setAddress(address);

        // checks payment information and updates paymentInformation object
        if(verifyPayment(cardNumberInput.getText(), CVVInput.getText())) {
            if(cardNumberInput.getText().length() != 0) {
                paymentInfo.setCardNumber(cardNumberInput.getText());
            }
            if(CVVInput.getText().length() != 0) {
                paymentInfo.setSecurityCode(Integer.parseInt(CVVInput.getText()));
            }
        }

        if(cardHolderInput.getText().length() != 0) {
            paymentInfo.setCardName(cardHolderInput.getText());
        }

        if(expiryDateInput.getText().length() != 0) {
            paymentInfo.setExpDate(expiryDateInput.getText());
        }
        patient.setPaymentInfo(paymentInfo);

        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB(); 

        // query to update patient values
        String savePatient = "UPDATE patient SET fname = '" + patient.getFirstName() + "', lname = '" + patient.getLastName() + "', email = '" + patient.getEmail() + 
                            "', phonenumber = '" + patient.getPhoneNumber() + "' WHERE patientID = '" + patient.getPatientID() + "'"; 
        
        // query to update patient address values
        String saveAddress = "UPDATE address SET addressLine1 = '" + address.getAddressLine1() + "', addressLine2 = '" + address.getAddressLine2() + "', city = '" + address.getCity() + 
                            "', state = '" + address.getState() + "', zip = '" + address.getZip() + "' WHERE patientID = '" + patient.getPatientID() + "'"; 
        
        // query to update payment information values
        String savePaymentInfo = "UPDATE paymentInformation SET cardNumber = '" + paymentInfo.getCardNumber() + "', cardHolder = '" + paymentInfo.getCardName() + "', expDate = '" + 
                                paymentInfo.getExpDate() + "', securityCode = '" + paymentInfo.getSecurityCode() + "' WHERE patientID = '" + patient.getPatientID() + "'";

        Statement statement = connection.createStatement();
        statement.executeUpdate(savePatient); // saves any updates made to patient profile to database
        statement.executeUpdate(saveAddress); // saves any updates made to address to database
        statement.executeUpdate(savePaymentInfo); // saves any updates made to paymentInformation to database

        App app = new App();
        app.loadHome(patient); // Updates home screen to new values of patient if changes were made

        if(errorText.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save Changes");
            alert.setHeaderText("Profile Updated Successfully.");
            alert.show();
            System.out.println("User Updated Profile.");
        }
    }

    /* Patient info is loaded as default on update profile page */
    public void loadPatientInfo() {
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

    /* Patient info is initialized */
    public void setPatientInfo(Patient patient, Address address, PaymentInformation paymentInfo) {
        this.patient = patient;
        this.address = address;
        this.paymentInfo = paymentInfo;
    }

    public boolean verifyPayment(String cardNumber, String CVV) {
        if(cardNumberInput.getText().length() != 0 && cardNumber.length() < 15) {
            errorText.setText("Invalid Card Number.");
            return false;
        }
        if(CVVInput.getText().length() != 0 && (Integer.parseInt(CVV) < 100 || Integer.parseInt(CVV) > 9999)) {
            errorText.setText("Invalid CVV.");
            return false;
        }
        return true;
    }
}
