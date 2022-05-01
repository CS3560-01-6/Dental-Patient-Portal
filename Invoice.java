public class Invoice 
{
    private int invoiceId;
    private double totalCost;
    private String paymentDueDate;
    private String invoiceStatus;
    private Patient patient;
    private Treatment treatment;

    public Invoice(int invoiceId, double totalCost, String paymentDueDate, String invoiceStatus, Patient patient, Treatment treatment) {
        this.invoiceId = invoiceId;
        this.totalCost = totalCost;
        this.paymentDueDate = paymentDueDate;
        this.invoiceStatus = invoiceStatus;
        this.patient = patient;
        this.treatment = treatment;
    }

    public int getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getPaymentDueDate() {
        return this.paymentDueDate;
    }

    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getInvoiceStatus() {
        return this.invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Treatment getTreatment() {
        return this.treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
    
}
