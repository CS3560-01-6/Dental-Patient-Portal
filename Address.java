public class Address
{
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int zip;

    public Address(String addressLine1,String addressLine2, String city, String state, int zip)
    {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.zip = zip;
    }

    public String toString()
    {
        return addressLine1 + ", " + addressLine2 + "\n" + city + "\t" + state + "\n" + zip + "\n";
    }
}