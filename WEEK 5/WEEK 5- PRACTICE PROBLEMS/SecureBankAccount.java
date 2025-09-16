public class SecureBankAccount {
    private final String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private int failedAttempts;

    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance >= MIN_BALANCE ? initialBalance : MIN_BALANCE;
        this.pin = 0;
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot view balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    public boolean setPin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            System.out.println("PIN changed successfully.");
            return true;
        }
        System.out.println("Incorrect old PIN.");
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked.");
            return false;
        }
        if (enteredPin == this.pin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            return false;
        }
    }

    public boolean unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked.");
            return true;
        }
        System.out.println("Incorrect PIN. Cannot unlock.");
        return false;
    }

    public boolean deposit(double amount, int pin) {
        if (validatePin(pin) && amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && balance - amount >= MIN_BALANCE) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount);
                return true;
            } else {
                System.out.println("Insufficient funds.");
            }
        }
        return false;
    }

    public boolean transfer(SecureBankAccount target, double amount, int pin) {
        if (withdraw(amount, pin)) {
            target.balance += amount;
            System.out.println("Transferred: " + amount + " to " + target.getAccountNumber());
            return true;
        }
        return false;
    }

    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to multiple failed attempts.");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        System.out.println("Failed attempt " + failedAttempts);
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        }
    }

    public static void main(String[] args) {
        SecureBankAccount acc1 = new SecureBankAccount("ACC123", 1000);
        SecureBankAccount acc2 = new SecureBankAccount("ACC456", 500);

        // acc1.balance = 99999; // error

        acc1.setPin(0, 1234);
        acc2.setPin(0, 5678);

        acc1.deposit(200, 1234);
        acc1.withdraw(100, 1234);

        acc1.transfer(acc2, 300, 1234);

        acc1.withdraw(5000, 1234);

        acc1.validatePin(1111);
        acc1.validatePin(2222);
        acc1.validatePin(3333);

        acc1.getBalance();

        acc1.unlockAccount(1234);
        acc1.getBalance();
    }
}
