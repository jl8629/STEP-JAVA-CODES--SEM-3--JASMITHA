abstract class Employee {
    protected String name;
    protected double salary;
    abstract void calculateBonus();
}

interface Payable {
    void generatePaySlip();
}

class Manager extends Employee implements Payable {
    private String department;
    private double bonus;
    Manager(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
    void calculateBonus() {
        bonus = salary * 0.2;
    }
    public void generatePaySlip() {
        System.out.println("Pay Slip for Manager: " + name);
        System.out.println("Department: " + department);
        System.out.println("Base Salary: " + salary);
        System.out.println("Bonus: " + bonus);
        System.out.println("Total Pay: " + (salary + bonus));
    }
}

public class Employees {
    public static void main(String[] args) {
        Manager m = new Manager("Alice", 75000, "Finance");
        m.calculateBonus();
        m.generatePaySlip();
    }
}
