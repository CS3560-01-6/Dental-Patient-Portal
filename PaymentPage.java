import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentPage {

    @FXML
    private TextField cvvInput;

    @FXML
    private Text errorMessage;

    @FXML
    private Text expiryDate;

    @FXML
    private Text lastFourDigits;

    @FXML
    private Button payButton;

    @FXML
    private Text total;

    Connection connection = null;
    Invoice invoice;
    Payment payment;

    /* Marks invoice as paid and adds payment */
    @FXML
    void payInvoice(ActionEvent event) throws Exception {

        int cvv = Integer.parseInt(cvvInput.getText());

        if(cvvInput.getText().isEmpty() || cvv != invoice.getPatient().getPaymentInfo().getSecurityCode()) {
            errorMessage.setText("Invalid CVV.");
        } else {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String datePaid = dtf.format(now).toString();

            payment = new Payment(invoice.getTotalCost(), invoice, datePaid.toString(), "Card");
            invoice.setInvoiceStatus("paid");

            addPayment();

            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
            ((Button) confirmation.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("OK");
            confirmation.setTitle("Successful Payment");
            confirmation.setHeaderText("Invoice has been successfully paid.");
            if(confirmation.showAndWait().get() == ButtonType.CANCEL) {
                Node node = (Node) event.getSource();
                Stage paymentPage = (Stage) node.getScene().getWindow();
                paymentPage.close();
            }
            System.out.println("Patient successfully paid for invoice #" + invoice.getInvoiceId());
        }

        App app = new App();
        app.loadHome(invoice.getPatient());
    }

    /* Initializes Payment Page with Default Payment Method on Profile and Invoice Total */
    public void loadPayment(Invoice invoice) {
        this.invoice = invoice;
        lastFourDigits.setText(invoice.getPatient().getPaymentInfo().getLastFourDigits());
        expiryDate.setText(invoice.getPatient().getPaymentInfo().getExpDate());
        total.setText("$" + invoice.getTotalCost());
    }

    /* Records payment to database */
    public void addPayment() throws Exception {
        Handler mysqlHandler = new Handler();
        connection = mysqlHandler.connectDB();
        Statement statement = connection.createStatement();

        String markInvoicePaid = "UPDATE invoice SET invoiceStatus = 'Paid' WHERE invoiceID = '" + invoice.getInvoiceId() + "'";
        statement.executeUpdate(markInvoicePaid);

        String getPaymentInfoID = "SELECT * FROM paymentInformation WHERE patientID = '" + invoice.getPatient().getPatientID() + "'";
        ResultSet result = statement.executeQuery(getPaymentInfoID);
        result.next();
        int paymentInfoID = Integer.parseInt(result.getString("paymentInfoID"));
        int paymentID = (int) (Math.random()*99999)+10000;
        String addPayment = "INSERT INTO payment VALUES (000" + paymentID + ", " + invoice.getInvoiceId() + ", " + paymentInfoID + ", '" + payment.getDatePaid() + "', " + payment.getAmount() + ", '" + payment.getPaymentType() + "')";
        statement.executeUpdate(addPayment);
    }

}