public class PaymentInformation
{
    private int cardNumber;
    private String lastFourDigits;
    private String nameOnCard;
    private String expDate;
    private int securityCode;

    public PaymentInformation(int cardNumber, String nameOncard, String expDate, int securityCode)
    {
        this.cardNumber = cardNumber;
        this.lastFourDigits = Integer.toString(cardNumber).substring(Integer.toString(cardNumber).length() - 4);
        this.nameOnCard = nameOncard;
        this.expDate = expDate;
        this.securityCode = securityCode;
    }

    public void updatePayment(int cardNumber, String nameOncard, String expDate, int securityCode)
    {
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOncard;
        this.expDate = expDate;
        this.securityCode = securityCode;
    }

    public void updateCardName(int cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public void updatePaymentName(String nameOnCard)
    {
        this.nameOnCard = nameOnCard;
    }

    public void updateExpDate(String expDate)
    {
        this.expDate = expDate;
    }

    public void updateSecurityCode(int securityCode)
    {
        this.securityCode = securityCode;
    }

    public int getCardNumber()
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

    public String toString()
    {
        return nameOnCard + "\n" + cardNumber + "\n" + expDate + "\t" + securityCode + "\n";
    }
}