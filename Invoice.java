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

    public void setTotalCost(double totalCost)
    {
        this.totalCost = totalCost;
    }

    public void setPaymentDueDate(String paymentDueDate)
    {
        this.paymentDueDate = paymentDueDate;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public double getTotalCost()
    {
        return totalCost;
    }

    public String getPaymentDueDate()
    {
        return paymentDueDate;
    }

    public Patient getPatient()
    {
        return patient;
    }

}
