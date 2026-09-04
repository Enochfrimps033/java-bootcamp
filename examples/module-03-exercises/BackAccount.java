public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance){
        this.accountNumber=accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (accountNumber != null && !accountNumber.isBlank()) {
            this.accountNumber = accountNumber;
        } else {
            System.out.println("Invalid account number");
        }

        public double getBalance () {
            return balance;
        }

        public void setBalance ( double balance){
            if (balance >= 0) {
                this.balance = balance
            } else {
                System.out.println("balance can not be negative!");
            }
        }
    }