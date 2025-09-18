package DesignerPattern.StrateryPattern;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public String toString() {
        return "PaymentContext with current strategy: " +
                (paymentStrategy != null ? paymentStrategy.getClass().getSimpleName() : "No strategy");
    }

    public void payAmount(int amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set!");
        }
        paymentStrategy.pay(amount);
    }
}
