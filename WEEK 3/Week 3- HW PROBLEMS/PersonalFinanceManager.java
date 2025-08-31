import java.util.Scanner;

class PersonalAccount {
    private String accountHolderName, accountNumber;
    private double currentBalance, totalIncome, totalExpenses;
    private static int totalAccounts=0, counter=1;
    private static String bankName="Default Bank";

    public PersonalAccount(String name){
        this.accountHolderName=name;
        this.accountNumber=generateAccountNumber();
        this.currentBalance=0;
        this.totalIncome=0;
        this.totalExpenses=0;
        totalAccounts++;
    }

    public void addIncome(double amount,String description){
        if(amount>0){
            currentBalance+=amount;
            totalIncome+=amount;
            System.out.println(accountHolderName+" Income added: "+amount+" ("+description+")");
        }
    }

    public void addExpense(double amount,String description){
        if(amount>0 && amount<=currentBalance){
            currentBalance-=amount;
            totalExpenses+=amount;
            System.out.println(accountHolderName+" Expense added: "+amount+" ("+description+")");
        } else {
            System.out.println("Insufficient funds for "+accountHolderName);
        }
    }

    public double calculateSavings(){
        return currentBalance;
    }

    public void displayAccountSummary(){
        System.out.println("\nAccount Summary for "+accountHolderName);
        System.out.println("Bank: "+bankName);
        System.out.println("Account Number: "+accountNumber);
        System.out.println("Total Income: "+totalIncome);
        System.out.println("Total Expenses: "+totalExpenses);
        System.out.println("Current Balance (Savings): "+currentBalance);
    }

    public static void setBankName(String name){ bankName=name; }
    public static int getTotalAccounts(){ return totalAccounts; }
    private static String generateAccountNumber(){ return "ACC"+(counter++); }
}

public class PersonalFinanceManager {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        PersonalAccount.setBankName("National Finance Bank");

        PersonalAccount acc1=new PersonalAccount("Alice");
        PersonalAccount acc2=new PersonalAccount("Bob");
        PersonalAccount acc3=new PersonalAccount("Charlie");

        acc1.addIncome(5000,"Salary");
        acc1.addExpense(1500,"Rent");
        acc1.addExpense(1000,"Groceries");

        acc2.addIncome(8000,"Salary");
        acc2.addIncome(2000,"Freelance");
        acc2.addExpense(3000,"Travel");

        acc3.addIncome(10000,"Business Profit");
        acc3.addExpense(4000,"Office Rent");
        acc3.addExpense(2000,"Supplies");

        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        System.out.println("\nTotal Accounts Created: "+PersonalAccount.getTotalAccounts());
        System.out.println("Bank Name (shared): "+ "National Finance Bank");

        sc.close();
    }
}
