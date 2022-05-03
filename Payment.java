public class Payment
{
    private double amount;
    private Invoice invoice;
    private String datePaid;
    private String paymentType;

    public Payment(double amount, Invoice invoice, String datePaid, String paymentType) {
        this.amount = amount;
        this.invoice = invoice;
        this.datePaid = datePaid;
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Invoice getInvoice() {
        return this.invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getDatePaid() {
        return this.datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}