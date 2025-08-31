import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int counter = 1;

    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }

    public double checkBalance() {
        return balance;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        String acc = String.format("ACC%03d", counter);
        counter++;
        return acc;
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of accounts to create: ");
        int n = sc.nextInt();
        BankAccount[] accounts = new BankAccount[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter account holder name: ");
            String name = sc.next();
            System.out.print("Enter initial deposit: ");
            double deposit = sc.nextDouble();
            accounts[i] = new BankAccount(name, deposit);
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\nTransactions for Account " + (i+1));
            accounts[i].deposit(1000);
            accounts[i].withdraw(500);
            accounts[i].displayAccountInfo();
            System.out.println("Current Balance: " + accounts[i].checkBalance());
        }

        System.out.println("\nTotal Accounts Created: " + BankAccount.getTotalAccounts());
        sc.close();
    }
}
