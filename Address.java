public class Address
{
    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String State;
    private int ZIP;
    private String Country;

    public Address(String AL1,String AL2, String CT, int z, String C)
    {
        AddressLine1 = AL1;
        AddressLine2 = AL2;
        City = CT;
        ZIP = z;
        Country = C;
    }
}