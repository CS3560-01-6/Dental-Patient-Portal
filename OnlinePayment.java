public class OnlinePayment extends Payment
{
    private String paymentDueDate;

    public OnlinePayment(String paymentDueDate, String data, int amount, boolean paidStatus, Treatment service)
    {
        super(data, amount, paidStatus, service);
        this.paymentDueDate = paymentDueDate;
    }
}