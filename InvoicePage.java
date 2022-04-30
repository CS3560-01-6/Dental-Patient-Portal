import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class InvoicePage {

    @FXML
    private Label cityStateZip;

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
        invoiceID.setText(Integer.toString(invoice.getInvoiceId()));
        invoicePaymentDueDate.setText((invoice.getPaymentDueDate()));
        invoiceStatus.setText(invoice.getInvoiceStatus());
        invoiceTotal.setText(Double.toString(invoice.getTotalCost()));
        patientName.setText(invoice.getPatient().getFirstName() + " " + invoice.getPatient().getLastName());
        patientEmail.setText(invoice.getPatient().getEmail());
        patientPhone.setText(invoice.getPatient().getPhoneNumber());
        patientAddress.setText(invoice.getPatient().getAddress().getAddressLine1());
        cityStateZip.setText(invoice.getPatient().getAddress().getCity() + ", " + invoice.getPatient().getAddress().getState() + " " + invoice.getPatient().getAddress().getZip());
        treatmentName.setText(invoice.getTreatment().getService());
        treatmentCost.setText(Double.toString(invoice.getTreatment().getCost()));

        if(invoice.getInvoiceStatus().equalsIgnoreCase("paid")) {
            payButton.setVisible(false);;
        }
    }

}