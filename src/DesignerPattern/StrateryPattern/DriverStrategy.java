package DesignerPattern.StrateryPattern;

public class DriverStrategy {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();
        paymentContext.setPaymentStrategy(new CreditCardPayment("Are khuch bhi do na"));
        paymentContext.payAmount(2);
        paymentContext.setPaymentStrategy(new UpiPayment(4689));
        paymentContext.payAmount(200);

        System.out.println(paymentContext.toString());
    }
}
