public class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount)
            throws InsufficientFundsException {
        // Validate before mutating state.
        if (amount > balance) {
            // TODO: throw new InsufficientFundsException(balance, amount)
            // what is the new for?
            throw new  InsufficientFundsException(balance, amount);
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}