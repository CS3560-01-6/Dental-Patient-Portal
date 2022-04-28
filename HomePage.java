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
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
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
    private ListView<Invoice> invoiceList;

    @FXML
    private TextField invoiceSearchBar;

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
    Patient patient;
    Address address;

    /* Logs user out and returns back to login page */
    @FXML
    void logout(ActionEvent event) throws IOException {
        App app = new App();
        app.changeScene("LoginScene.fxml"); // returns user to log in screen
        System.out.println("Patient logged out successfully.");
    }

    /* Prompts the Update Profile Page. */
    @FXML
    void updateProfile(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/UpdateProfileScene.fxml")); // loads UpdateProfileScene.fxml
        Parent root = loader.load();

        UpdateProfilePage updateProfileController = loader.getController(); // grants access to class controller and class variables
        updateProfileController.loadPatientInfo(patient, address);

        // Opens Update Profile Window
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Update Profile");
        stage.setScene(scene);
        stage.show();

        System.out.println("Update Profile popup launched successfully.");
    }

    /* Displays default patient information on home page. */
    public void displayPatientInfo() throws Exception {

        // displays default patient address information
        this.patientID.setText(Integer.toString(patient.getPatientID()));
        this.firstName.setText(patient.getFirstName());
        this.lastName.setText(patient.getLastName());
        this.dateOfBirth.setText(patient.getDateOfBirth());
        this.email.setText(patient.getEmail());
        this.phoneNumber.setText(patient.getPhoneNumber());

        // displays default patient address information
        this.addressLine1.setText(address.getAddressLine1());
        this.addressLine2.setText(address.getAddressLine2());
        this.city.setText(address.getCity());
        this.state.setText(address.getState());
        this.zip.setText(Integer.toString(address.getZip()));
    }

    /* Initializes Patient with a PatientID */
    public void setPatient(Patient patient) throws Exception {

        if(patient.getFirstName() != null) { // patient already logged in
            this.patient = patient;
            this.address = patient.getAddress();
            displayPatientInfo();
        } else { // new patient logging in
            setAddress(patient.getPatientID());

            Handler sqlConnection = new Handler();
            connection = sqlConnection.connectDB();
    
            String getPatientInfo = "SELECT * FROM patient WHERE patientID = '" + patient.getPatientID() + "'";
    
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getPatientInfo);
            
            result.next();
            this.patient = new Patient(patient.getPatientID(), result.getString("password"), result.getString("fname"), result.getString("lname"), 
                                result.getString("dateofbirth"), result.getString("email"), result.getString("phonenumber"), address);
    
            displayPatientInfo();
        }
        
    }

    /* Initializes Address */
    public void setAddress(int patientID) throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();

        String getAddressInfo = "SELECT * FROM address WHERE patientID = '" + patientID + "'";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getAddressInfo);

        result.next();
        address = new Address(result.getString("addressLine1"), result.getString("addressLine2"), result.getString("city"), result.getString("state"), Integer.parseInt(result.getString("zip")));
    }

    public void setInvoiceList(Patient patient) throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();

        // SQL query to get all invoices pertaining to patient in database
        String getInvoices = "";
    }

    public void loadInvoiceList(int patientID) throws Exception {
        
    }
}