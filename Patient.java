public class Patient
{
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private int insuranceID;
    private Address address;

    public Patient(String firstName, String lastName, String dateOfBirth, String email, int insuranceID, Address address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.insuranceID = insuranceID;
        this.address = address;
    }
}