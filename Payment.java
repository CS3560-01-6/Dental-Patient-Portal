public class Payment
{
    private String data;
    private int amount;
    private boolean paidStatus;
    private Treatment service;
    private String paymentDueDate;
    private String paymentType;

    public Payment(String data, int amount, boolean paidStatus, Treatment service, String paymentDueDate, String paymentType)
    {
        this.data = data;
        this.amount = amount;
        this.paidStatus = paidStatus;
        this.service = service;
        this.paymentDueDate = paymentDueDate;
        this.paymentType = paymentType;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
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

    public Treatment getService() {
        return this.service;
    }

    public void setService(Treatment service) {
        this.service = service;
    }

    public String getPaymentDueDate() {
        return this.paymentDueDate;
    }

    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}