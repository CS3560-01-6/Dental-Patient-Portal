public class Treatment 
{
    private int treatmentID;
    private String service;
    private double cost;

    public Treatment(int treatmentID, String service, double cost) {
        this.treatmentID = treatmentID;
        this.service = service;
        this.cost = cost;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTreatmentID() {
        return this.treatmentID;
    }

    public void setTreatmentID(int treatmentID) {
        this.treatmentID = treatmentID;
    }

}
