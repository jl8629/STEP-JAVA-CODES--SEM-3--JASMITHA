public class BankAccount {
    static String bankName;
    static int totalAccounts = 0;
    static double interestRate;

    String accountNumber;
    String accountHolder;
    double balance;

    BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        totalAccounts++;
    }

    static void setBankName(String name) {
        bankName = name;
    }

    static void setInterestRate(double rate) {
        interestRate = rate;
    }

    static int getTotalAccounts() {
        return totalAccounts;
    }

    static void displayBankInfo() {
        System.out.println("Bank Name: " + bankName);
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("\n");
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. New Balance: " + balance);
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn. New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    double calculateInterest() {
        return balance * interestRate / 100;
    }

    void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: " + balance);
        System.out.println("Interest: " + calculateInterest());
        System.out.println("\n");
    }

    public static void main(String[] args) {
        BankAccount.setBankName("Global Bank");
        BankAccount.setInterestRate(5.0);

        BankAccount a1 = new BankAccount("ACC101", "Alice", 1000);
        BankAccount a2 = new BankAccount("ACC102", "Bob", 2000);

        displayBankInfo();

        a1.displayAccountInfo();
        a2.displayAccountInfo();

        a1.deposit(500);
        a2.withdraw(300);

        System.out.println("Total Accounts via object: " + a1.getTotalAccounts());
        System.out.println("Total Accounts via class: " + BankAccount.getTotalAccounts());

        BankAccount.setInterestRate(6.0);
        System.out.println("Interest after change:");
        a1.displayAccountInfo();
        a2.displayAccountInfo();
    }
}
