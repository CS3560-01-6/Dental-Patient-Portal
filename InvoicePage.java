import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InvoicePage {

    @FXML
    private Label cityStateZip;

    @FXML
    private Button closeButton;

    @FXML
    private Text datePaid;

    @FXML
    private Text invoiceID;

    @FXML
    private Text invoicePaymentDueDate;

    @FXML
    private Text invoiceStatus;

    @FXML
    private Label invoiceTotal;

    @FXML
    private GridPane paidGridPane;

    @FXML
    private Label patientAddress;

    @FXML
    private Label patientEmail;

    @FXML
    private Label patientName;

    @FXML
    private Label patientPhone;

    @FXML
    private Button payButton;

    @FXML
    private Text paymentID;

    @FXML
    private Text paymentMethod;

    @FXML
    private Label treatmentCost;

    @FXML
    private Label treatmentName;

    Invoice invoice;
    Connection connection = null;

    /* Prompts the page/popup for Payment Method. (Functionality of Pay Button) */
    @FXML
    void payInvoice(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/PaymentScene.fxml"));
        Parent root = loader.load();

        PaymentPage paymentPageController = loader.getController();
        paymentPageController.loadPayment(invoice);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Pay Invoice");
        stage.setScene(scene);
        stage.show();

        System.out.println("Payment Page popup launched successfully.");
    }

    /* Brings screen back to the list of invoices in home page. (Functionality of return button) */
    @FXML
    void closeInvoice(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage invoiceStage = (Stage) node.getScene().getWindow();
        invoiceStage.close();
    }

    /* Loads invoice information on the invoice page */
    public void loadInvoice(Invoice invoice) throws Exception {
        this.invoice = invoice;
        invoiceID.setText(Integer.toString(invoice.getInvoiceId()));
        invoicePaymentDueDate.setText((invoice.getPaymentDueDate()));
        invoiceStatus.setText(invoice.getInvoiceStatus());
        invoiceTotal.setText("$" + Double.toString(invoice.getTotalCost()));
        patientName.setText(invoice.getPatient().getFirstName() + " " + invoice.getPatient().getLastName());
        patientEmail.setText(invoice.getPatient().getEmail());
        patientPhone.setText(invoice.getPatient().getPhoneNumber());
        patientAddress.setText(invoice.getPatient().getAddress().getAddressLine1());
        cityStateZip.setText(invoice.getPatient().getAddress().getCity() + ", " + invoice.getPatient().getAddress().getState() + " " + invoice.getPatient().getAddress().getZip());
        treatmentName.setText(invoice.getTreatment().getService());
        treatmentCost.setText("$" + Double.toString(invoice.getTreatment().getCost()));
        paidGridPane.setVisible(false);

        if(invoice.getInvoiceStatus().equalsIgnoreCase("paid")) { // if invoice is paid, show pay button and display payment information
            payButton.setVisible(false);
            paidGridPane.setVisible(true);

            displayPayment(invoice);
        }
    }

    /* Displays payment information for paid invoices */
    public void displayPayment(Invoice invoice) throws Exception {
        Handler mysqlConnection = new Handler();
        connection = mysqlConnection.connectDB();

        String getPayment = "SELECT * FROM payment WHERE invoiceID = '" + invoiceID.getText() + "'";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getPayment);
        result.next();
        
        paymentID.setText(result.getString("paymentID"));
        datePaid.setText(result.getString("datePaid"));
        paymentMethod.setText(result.getString("paymentType"));
    }
}