public class InheritanceDemo {
    public static void main(String[] args) {
        // TODO: base-type array holding SavingsAccount(100) and CurrentAccount(100)
        //these are 2 obj i have created
        //subclasses SavingsAccount and CurrentAccount are allowed inside Account(parent class)
        //bc both extend Account
         Account[] accounts = {new SavingsAccount(100)  ,new CurrentAccount(100), new FrozenAccount(100)};

        for (Account account : accounts) {
            // TODO: capture withdraw result; print type, ok flag, and balance
            boolean ok = account.withdraw(20.00);
            System.out.printf("%s withdraw=%s balance=%.2f%n",
                    account.getAccountType(), ok, account.getBalance());
        }
    }
}