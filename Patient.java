public class Patient
{
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private int insuranceID;
    private Address address;
    private PaymentInformation card;

    public Patient(String firstName, String lastName, String dateOfBirth, String email, int insuranceID, Address address, PaymentInformation card)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.insuranceID = insuranceID;
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

    public void setInsuranceID(int insuranceID)
    {
        this.insuranceID = insuranceID;
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

    public int getInsuranceID()
    {
        return insuranceID;
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