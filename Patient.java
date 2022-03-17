import Address;
public class Patient
{
    private String FirstName;
    private String LastName;
    private String DateOFBirth;
    private String Email;
    private Address Address;
    private int InsuranceID;

    public Patient(String FN, String LN, String DOB, String EM)
    {
        FirstName = FN;
        LastName = LN;
        DateOFBirth = DOB;
        Email = EM;
    }
}