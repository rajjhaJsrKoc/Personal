package DesignerPattern.StrateryPattern;

public class UpiPayment implements PaymentStrategy{
    int upiId;
    public UpiPayment(int upiId) {
        this.upiId = upiId;
    }
    @Override
    public void pay(int amount) {
        System.out.println(upiId + " payed " + amount);
    }
}
