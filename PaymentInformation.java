public class PaymentInformation
{
    private int CardNumber;
    private String NameOnCard;
    private String ExpDate;
    private int SecurityCode;

    Public PaymentInformation(int CN, String NOC, String ED, int SC)
    {
        CardNumber = CN;
        NameOnCard = NOC;
        ExpDate = ED;
        SecurityCode = SC;
    }
}