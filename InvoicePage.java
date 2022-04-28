import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InvoicePage {

    @FXML
    private Text invoiceID;

    @FXML
    private Text invoicePaymentDueDate;

    @FXML
    private Text invoiceStatus;

    @FXML
    private Label invoiceTotal;

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
    private Button returnButton;

    @FXML
    private Label treatmentCost;

    @FXML
    private Label treatmentName;

    Connection connection = null;

    /* Prompts the page/popup for Payment Method. (Functionality of Pay Button) */
    @FXML
    void payInvoice(ActionEvent event) {

    }

    /* Brings screen back to the list of invoices in home page. (Functionality of return button) */
    @FXML
    void returnToInvoiceList(ActionEvent event) {
        
    }

    /* Loads invoice information on the invoice page. Show pay button if status is unpaid, hide pay button if status is paid */
    public void loadInvoice(Invoice invoice) throws Exception {
        Handler sqlConnection = new Handler();
        connection = sqlConnection.connectDB();

        
    }

}