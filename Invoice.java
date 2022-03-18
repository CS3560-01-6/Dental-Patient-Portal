public class Invoice 
{
    private int invoiceId;
    private double totalCost;
    private String paymentDueDate;
    private Patient patient;

    public Invoice(int invoiceId, double totalCost, String paymentDueDate, Patient patient)
    {
        this.invoiceId = invoiceId;
        this.totalCost = totalCost;
        this.paymentDueDate = paymentDueDate;
        this.patient = patient;
    }
}
