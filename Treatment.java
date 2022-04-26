public class Treatment 
{
    private String service;
    private double cost;

    public Treatment(String service, double cost)
    {
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
}
