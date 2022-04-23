public class Patient
{
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private int patientID;
    private Address address;
    private PaymentInformation card;

    public Patient(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, int patientID, Address address, PaymentInformation card)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patientID = patientID;
        this.address = address;
        this.card = card;
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

    public void setCard(PaymentInformation card)
    {
        this.card = card;
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

    public String getAddress()
    {
        return address.toString();
    }

    public String getCard()
    {
        return card.toString();
    }
}