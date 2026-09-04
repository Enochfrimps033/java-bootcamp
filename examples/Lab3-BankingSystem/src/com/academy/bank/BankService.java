package com.academy.bank;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BankService {

    private static final int MAX_CUSTOMERS = 50;
    private static final int MAX_ACCOUNTS = 100;
    private static final int MAX_TRANSACTIONS = 500;

    private final Customer[] customers = new Customer[MAX_CUSTOMERS];
    private final Account[] accounts = new Account[MAX_ACCOUNTS];
    private final Transaction[] transactions = new Transaction[MAX_TRANSACTIONS];

    private int customerCount = 0;
    private int accountCount = 0;
    private int transactionCount = 0;
    private int nextAccountNumber = 10001;
    private int nextTransactionNumber = 1;

    private final Scanner scanner;

    public BankService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createCustomer() {
        System.out.print("Customer Id: ");
        String customerId = scanner.nextLine();

        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equals(customerId)) {
                System.out.println("Customer ID already exists.");
                return;
            }
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(customerId, name, email, phone);

        customers[customerCount] = customer;
        customerCount++;

        System.out.println("Customer Created Successfully.");
    }

    public void createSavingsAccount() {
        System.out.print("Customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = null;

        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equals(customerId)) {
                customer = customers[i];
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Initial Balance: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());

        System.out.print("Interest Rate (%): ");
        double interestRate = Double.parseDouble(scanner.nextLine());

        String accountNumber = String.valueOf(nextAccountNumber++);

        SavingsAccount savingsAccount =
                new SavingsAccount(accountNumber, initialBalance, customer, interestRate);

        accounts[accountCount] = savingsAccount;
        accountCount++;

        System.out.println("Savings Account Created.");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance : " + initialBalance);
        System.out.println("Interest Rate : " + interestRate + "%");
    }

    public void createCurrentAccount() {
        System.out.print("Customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = null;

        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equals(customerId)) {
                customer = customers[i];
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Initial Balance: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());

        System.out.print("Transaction Fee: ");
        double transactionFee = Double.parseDouble(scanner.nextLine());

        String accountNumber = String.valueOf(nextAccountNumber++);

        CurrentAccount currentAccount =
                new CurrentAccount(accountNumber, initialBalance, customer, transactionFee);

        accounts[accountCount] = currentAccount;
        accountCount++;

        System.out.println("Current Account Created.");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance : " + initialBalance);
        System.out.println("Transaction Fee : " + transactionFee);
    }

    public void deposit() {
        System.out.print("Account Number: ");
        String accountNumber = scanner.nextLine();

        Account account = null;

        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                account = accounts[i];
                break;
            }
        }

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Deposit Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        account.deposit(amount);

        recordTransaction(accountNumber, amount, "DEPOSIT");

        System.out.println("Balance Updated : " + account.getBalance());
    }

    public void withdraw() {
        System.out.print("Account Number: ");
        String accountNumber = scanner.nextLine();

        Account account = null;

        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                account = accounts[i];
                break;
            }
        }

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());

        double fee = account.calculateCharges();

        boolean success = account.withdraw(amount);

        if (!success) {
            System.out.println("Insufficient funds.");
            return;
        }

        recordTransaction(accountNumber, amount, "WITHDRAW");

        if (account instanceof CurrentAccount) {
            System.out.println("Transaction Fee : " + fee);
            System.out.println("Total Deducted : " + (amount + fee));
        }

        System.out.println("Balance Updated : " + account.getBalance());
    }

    public void displayAccounts() {
        if (accountCount == 0) {
            System.out.println("No accounts found.");
            return;
        }

        for (int i = 0; i < accountCount; i++) {
            accounts[i].displayAccount();
        }
    }

    public void displayCustomers() {
        if (customerCount == 0) {
            System.out.println("No customers available.");
            return;
        }

        System.out.println("----------------------------------");

        for (int i = 0; i < customerCount; i++) {
            customers[i].display();
            System.out.println("----------------------------------");
        }
    }

    public void transferMoney() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayTransactionHistory() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayAccountsSortedByBalance() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayHighestBalanceCustomer() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void generateAccountSummaryReport() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    private Customer readExistingCustomer() {
        if (customerCount == 0) {
            System.out.println("Create a customer first.");
            return null;
        }

        System.out.print("Customer ID : ");
        String customerId = scanner.nextLine().trim();

        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
        }

        return customer;
    }

    private Account readExistingAccount() {
        if (accountCount == 0) {
            System.out.println("No accounts available.");
            return null;
        }

        System.out.print("Account Number : ");
        String accountNumber = scanner.nextLine().trim();

        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
        }

        return account;
    }

    private Customer findCustomer(String customerId) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equalsIgnoreCase(customerId)) {
                return customers[i];
            }
        }

        return null;
    }

    private Account findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }

        return null;
    }

    private void recordTransaction(String accountNumber, double amount, String type) {
        if (transactionCount >= MAX_TRANSACTIONS) {
            return;
        }

        String transactionId = "T" + nextTransactionNumber++;
        String date = LocalDate.now().toString();

        transactions[transactionCount++] =
                new Transaction(transactionId, amount, type, date, accountNumber);
    }

    private double readPositiveAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                double value = Double.parseDouble(input);

                if (value < 0) {
                    System.out.println("Amount must not be negative.");
                    continue;
                }

                return value;

            } catch (NumberFormatException ex) {
                System.out.println("Invalid amount. Please try again.");
            }
        }
    }
}