package payment;

/**
 * Code for this package is based on Spring Start in Action chapter 11.
 */
public class Payment {
    private String id;
    private double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
