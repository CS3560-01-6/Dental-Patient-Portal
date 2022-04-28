public class Patient
{
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private int patientID;
    private Address address;
    private String password;

    public Patient(int patientID, String password, String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, Address address)
    {
        this.patientID = patientID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Patient(int patientID) {
        this.patientID = patientID;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPatientID(int patientID)
    {
        this.patientID = patientID;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public int getPatientID()
    {
        return patientID;
    }

    public Address getAddress()
    {
        return address;
    }

    public String getPassword() {
        return password;
    }
}