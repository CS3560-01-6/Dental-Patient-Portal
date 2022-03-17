public class PaymentInformation
{
    private int cardNumber;
    private String nameOnCard;
    private String expDate;
    private int securityCode;

    public PaymentInformation(int cardNuber, String nameOncard, String expDate, int securityCode)
    {
        this.cardNumber = cardNuber;
        this.nameOnCard = nameOncard;
        this.expDate = expDate;
        this.securityCode = securityCode;
    }
}