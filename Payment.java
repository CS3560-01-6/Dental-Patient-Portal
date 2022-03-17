public class Payment
{
    private String data;
    private int amount;
    private boolean paidStatus;
    private Treatment service;

    public Payment(String data, int amount, boolean paidStatus, Treatment service)
    {
        this.data = data;
        this.amount = amount;
        this.paidStatus = paidStatus;
        this.service = service;
    }
}