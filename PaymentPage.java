import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PaymentPage {

    @FXML
    private TextField cvvInput;

    @FXML
    private Text expiryDate;

    @FXML
    private Text lastFourDigits;

    @FXML
    private Button payButton;

    @FXML
    private Text total;

    @FXML
    void payInvoice(ActionEvent event) {

    }

    public void loadPayment(Invoice invoice) {
        lastFourDigits.setText(invoice.getPatient().getPaymentInfo().getLastFourDigits());
        expiryDate.setText(invoice.getPatient().getPaymentInfo().getExpDate());
        total.setText("$" + invoice.getTotalCost());
    }

}