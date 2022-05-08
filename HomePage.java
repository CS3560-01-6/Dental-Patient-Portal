import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.*;
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
    private BorderPane invoicesBorderPane;

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

    @FXML
    private Hyperlink deleteAccountButton;

    @FXML
    private TableColumn<Payment, String> datePaidCol;

    @FXML
    private TableColumn<Payment, String> amountCol;

    @FXML
    private TableColumn<Payment, String> paymentTypeCol;

    @FXML
    private TableView<Payment> paymentHistory;

    Connection connection = null;
    Patient patient;
    Address address;
    PaymentInformation paymentInfo;
    ObservableList<Invoice> invoiceObservableList = FXCollections.observableArrayList();
    ObservableList<Payment> paymentObservableList = FXCollections.observableArrayList();

    /* Initializes Home Page with default values */
    public void initializeHomePage(Patient patient) throws Exception {
        if(patient.getFirstName() != null) { // patient already logged in
            this.patient = patient;
            this.address = patient.getAddress();
            this.paymentInfo = patient.getPaymentInfo();
        } else { // new patient logging in
            setAddress(patient.getPatientID());
            setPaymentInfo(patient.getPatientID());
            setPatient(patient);
        }
        setInvoiceList();
        setPaymentHistory();
        displayPatientProfile();
    }

    /* Displays default patient information on home page. */
    public void displayPatientProfile() throws Exception {

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

        // displays last 4 digits of card information
        this.lastFourDigits.setText(paymentInfo.getLastFourDigits());
    }

    /* Changes scene to invoice information view page */
    public void viewInvoice(Invoice invoice) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/InvoiceScene.fxml"));
        Parent root = loader.load();

        InvoicePage invoicePageController = loader.getController();
        invoicePageController.loadInvoice(invoice);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Update Profile");
        stage.setScene(scene);
        stage.show();

        System.out.println("Patient viewing Invoice " + invoice.getInvoiceId());
    }

    /* Logs user out and returns back to login page */
    @FXML
    void logout(ActionEvent event) throws IOException {
        App app = new App();
        app.changeScene("LoginScene.fxml"); // returns user to log in screen
        System.out.println("Patient logged out successfully.");
    }

    /* Prompts the Update Profile Page. */
    @FXML
    void updateProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/UpdateProfileScene.fxml")); // loads UpdateProfileScene.fxml
        Parent root = loader.load();

        UpdateProfilePage updateProfileController = loader.getController(); // grants access to class controller and class variables
        updateProfileController.setPatientInfo(patient, patient.getAddress(), patient.getPaymentInfo());
        updateProfileController.loadPatientInfo();

        // Opens Update Profile Window
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Update Profile");
        stage.setScene(scene);
        stage.show();

        System.out.println("Update Profile popup launched successfully.");
    }

    /* Initializes Patient with a PatientID */
    public void setPatient(Patient patient) throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();

        String getPatientInfo = "SELECT * FROM patient WHERE patientID = '" + patient.getPatientID() + "'";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getPatientInfo);
        
        result.next();
        this.patient = new Patient(patient.getPatientID(), result.getString("password"), result.getString("fname"), result.getString("lname"), 
                            result.getString("dateofbirth"), result.getString("email"), result.getString("phonenumber"), address, paymentInfo);
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

    /* Initializes Payment Information */
    public void setPaymentInfo(int PatientID) throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();

        String getPaymentInfo = "SELECT * FROM paymentInformation WHERE patientID = '" + PatientID + "'";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getPaymentInfo);

        result.next();
        paymentInfo = new PaymentInformation(result.getString("cardNumber"), result.getString("cardHolder"), result.getString("expDate"), Integer.parseInt(result.getString("securityCode")));
    }

    /* Custom class to display view button next to list of invoices */
    static class Cell extends ListCell<Invoice> {
        HBox hbox = new HBox();
        Label label = new Label("");
        Pane pane = new Pane();
        Button button = new Button("View");
        Invoice invoice;

        public Cell() {
            super();

            hbox.getChildren().addAll(label, button);
            button.setStyle("-fx-background-color:  #2C7630; -fx-text-fill: white");
            button.setOnAction(event -> {
                try {
                    App app = new App();
                    app.loadInvoice(invoice);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }); 
        }

        @Override
        protected void updateItem(Invoice invoice, boolean empty) {
            super.updateItem(invoice, empty);
            setText(null);
            setGraphic(null);
            this.invoice = invoice;
            if (invoice != null && !empty) {
                setText("\t" + Integer.toString(invoice.getInvoiceId()));
                setGraphic(hbox);
            }
        }
    }

    /* Initializes list of invoices */
    public void setInvoiceList() throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();

        // SQL query to get all invoices pertaining to patient in database
        String getInvoices = "SELECT * FROM invoice WHERE patientID = '" + patient.getPatientID() + "'";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getInvoices);

        while(result.next()) {
            int invoiceId = Integer.parseInt(result.getString("invoiceID"));
            Double totalCost = Double.parseDouble(result.getString("totalCost"));
            String paymentDueDate = result.getString("paymentDueBy");
            String invoiceStatus = result.getString("invoiceStatus");
            Treatment treatment = getTreatment(invoiceId);

            invoiceObservableList.add(new Invoice(invoiceId, totalCost, paymentDueDate, invoiceStatus, patient, treatment));
        }
        invoiceList.setItems(invoiceObservableList);
        invoiceList.setCellFactory(param -> new Cell());
        System.out.println("Successfully set list of invoices.");
    }

    /* Retrieves treatment for a specific invoice */
    public Treatment getTreatment(int invoiceID) throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();

        String getTreatmentInfo = "SELECT * FROM treatment WHERE invoiceID = '" + invoiceID + "'";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getTreatmentInfo);

        result.next();
        Treatment treatment = new Treatment(Integer.parseInt(result.getString("treatmentID")), result.getString("service"), Double.parseDouble(result.getString("cost")));

        return treatment;
    }

    public void setPaymentHistory() throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();
        Statement statement = connection.createStatement();

        String getPaymentInfoID = "SELECT * FROM paymentInformation WHERE patientID = '" + patient.getPatientID() + "'";
        ResultSet result = statement.executeQuery(getPaymentInfoID);
        result.next();

        int paymentInfoID = Integer.parseInt(result.getString("paymentInfoID"));

        String getPayments = "SELECT * FROM payment WHERE paymentInfoID = '" + paymentInfoID + "'";
        result = statement.executeQuery(getPayments);

        while(result.next()) {
            Double amount = Double.parseDouble(result.getString("amount"));
            Invoice invoice = getInvoice(Integer.parseInt(result.getString("invoiceID")));
            String datePaid = result.getString("datePaid");
            String paymentType = result.getString("paymentType");
            paymentObservableList.add(new Payment(amount, invoice, datePaid, paymentType));
        }

        datePaidCol.setCellValueFactory(new PropertyValueFactory<>("datePaid"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentTypeCol.setCellValueFactory(new PropertyValueFactory<>("paymentType"));

        paymentHistory.setItems(paymentObservableList);
    }

    public Invoice getInvoice(int invoiceID) throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();
        Statement statement = connection.createStatement();

        String getInvoiceInfo = "SELECT * FROM invoice WHERE invoiceID = '" + invoiceID + "'";

        ResultSet result = statement.executeQuery(getInvoiceInfo);
        result.next();

        int invoiceId = Integer.parseInt(result.getString("invoiceID"));
        Double totalCost = Double.parseDouble(result.getString("totalCost"));
        String paymentDueDate = result.getString("paymentDueBy");
        String invoiceStatus = result.getString("invoiceStatus");
        Treatment treatment = getTreatment(invoiceId);

        Invoice invoice = new Invoice(invoiceId, totalCost, paymentDueDate, invoiceStatus, patient, treatment);

        return invoice;
    }

    @FXML
    void deleteAccount(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Account");
        alert.setHeaderText("Are you sure you want to delete your account?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            Handler sqlConnection = new Handler();
            connection = sqlConnection.connectDB();
            Statement statement = connection.createStatement();

            App app = new App();
            app.changeScene("LoginScene.fxml");

            String getPaymentInfoID = "SELECT * FROM paymentInformation WHERE patientID = '" + patient.getPatientID() + "'";
            ResultSet result = statement.executeQuery(getPaymentInfoID);
            result.next();

            int paymentInfoID = Integer.parseInt(result.getString("paymentInfoID"));

            String removePayments = "DELETE FROM payment WHERE paymentInfoID = '" + paymentInfoID + "'";
            String removePaymentInfo = "DELETE FROM paymentInformation WHERE patientID = '" + patient.getPatientID() + "'";
            String removeInvoices = "DELETE FROM invoice WHERE patientID = '" + patient.getPatientID() + "'";
            String removeAddress = "DELETE FROM address WHERE patientID = '" + patient.getPatientID() + "'";
            String removeAccount = "DELETE FROM patient WHERE patientID = '" + patient.getPatientID() + "'";
            statement.executeUpdate(removePayments);
            int[] invoiceIDs = new int[invoiceList.getItems().size()];
            for(int i = 0; i < invoiceIDs.length; i++) {
                invoiceIDs[i] = invoiceList.getItems().get(i).getInvoiceId();
                System.out.println("Invoice " + invoiceIDs[i] + " deleted.");
                String removeTreatments = "DELETE FROM treatment WHERE invoiceID = '" + invoiceIDs[i] + "'";
                statement.executeUpdate(removeTreatments);
            }
            statement.executeUpdate(removeInvoices);
            statement.executeUpdate(removePaymentInfo);
            statement.executeUpdate(removeAddress);
            statement.executeUpdate(removeAccount);
            System.out.println("User deleted account.");
        }
    }

}