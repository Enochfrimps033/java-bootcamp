public abstract class AbstractAccount {
    protected double balance;

    public AbstractAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    // TODO: abstract method — no body; every concrete subclass must implement
    // every concrete child class must provide its own version of getAccountType(
    public abstract String getAccountType();
}