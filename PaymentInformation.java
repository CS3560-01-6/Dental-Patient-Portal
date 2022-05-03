public class PaymentInformation
{
    private String cardNumber;
    private String lastFourDigits;
    private String nameOnCard;
    private String expDate;
    private int securityCode;

    public PaymentInformation(String cardNumber, String nameOncard, String expDate, int securityCode)
    {
        this.cardNumber = cardNumber;
        this.lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        this.nameOnCard = nameOncard;
        this.expDate = expDate;
        this.securityCode = securityCode;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public void setCardName(String nameOnCard)
    {
        this.nameOnCard = nameOnCard;
    }

    public void setExpDate(String expDate)
    {
        this.expDate = expDate;
    }

    public void setSecurityCode(int securityCode)
    {
        this.securityCode = securityCode;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public String getLastFourDigits() 
    {
        return lastFourDigits;
    }

    public String getCardName()
    {
        return nameOnCard;
    }

    public String getExpDate()
    {
        return expDate;
    }

    public int getSecurityCode()
    {
        return securityCode;
    }
}