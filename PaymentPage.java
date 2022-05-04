import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    @FXML
    void payInvoice(ActionEvent event) throws Exception {
        Handler mysqlHandler = new Handler();
        connection = mysqlHandler.connectDB();

        int cvv = Integer.parseInt(cvvInput.getText());

        if(cvvInput.getText().isEmpty() || cvv != invoice.getPatient().getPaymentInfo().getSecurityCode()) {
            errorMessage.setText("Invalid CVV.");
        } else {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String datePaid = dtf.format(now).toString();

            payment = new Payment(invoice.getTotalCost(), invoice, datePaid.toString(), "Card");

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
    }

    /* Initializes Payment Page with Default Payment Method on Profile and Invoice Total */
    public void loadPayment(Invoice invoice) {
        this.invoice = invoice;
        lastFourDigits.setText(invoice.getPatient().getPaymentInfo().getLastFourDigits());
        expiryDate.setText(invoice.getPatient().getPaymentInfo().getExpDate());
        total.setText("$" + invoice.getTotalCost());
    }

    public void addPayment() {

    }

}