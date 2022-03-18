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
}