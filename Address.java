public class Address
{
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int zip;
    private String country;

    public Address(String addressLine1,String addressLine2, String city, String state, int zip, String country)
    {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }
}