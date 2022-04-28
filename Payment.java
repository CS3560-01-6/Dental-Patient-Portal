public class Payment
{
    private int amount;
    private boolean paidStatus;
    private Invoice invoice;
    private String datePaid;
    private String paymentType;

    public Payment(int amount, boolean paidStatus, Invoice invoice, String datePaid, String paymentType) {
        this.amount = amount;
        this.paidStatus = paidStatus;
        this.invoice = invoice;
        this.datePaid = datePaid;
        this.paymentType = paymentType;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isPaidStatus() {
        return this.paidStatus;
    }

    public boolean getPaidStatus() {
        return this.paidStatus;
    }

    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
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