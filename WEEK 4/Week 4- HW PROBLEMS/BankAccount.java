import java.util.*;

public class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    public BankAccount() {
        this("Unknown", 0.0);
    }

    public BankAccount(String accountHolder) {
        this(accountHolder, 0.0);
    }

    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = new Random().nextInt(900000) + 100000;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. New Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn. New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    public void displayAccount() {
        System.out.println("Account Holder: " + accountHolder + " | Account Number: " + accountNumber + " | Balance: " + balance);
    }

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount("Alice");
        BankAccount acc3 = new BankAccount("Bob", 5000);

        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();

        acc2.deposit(2000);
        acc2.withdraw(500);
        acc2.displayAccount();

        acc3.withdraw(6000);
        acc3.deposit(1500);
        acc3.displayAccount();
    }
}
