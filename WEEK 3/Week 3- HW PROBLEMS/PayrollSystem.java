import java.util.*;

abstract class Employee {
    String empId, empName, department, designation, joinDate;
    double baseSalary;
    boolean[] attendanceRecord;
    static int totalEmployees = 0;
    static String companyName = "TechCorp Pvt Ltd";
    static double totalSalaryExpense = 0;
    static int workingDaysPerMonth = 30;

    Employee(String empId, String empName, String department, String designation,
             double baseSalary, String joinDate) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
        this.attendanceRecord = new boolean[workingDaysPerMonth];
        totalEmployees++;
    }

    void markAttendance(int day, boolean present) {
        if (day >= 1 && day <= workingDaysPerMonth) {
            attendanceRecord[day - 1] = present;
        }
    }

    int getPresentDays() {
        int count = 0;
        for (boolean d : attendanceRecord) if (d) count++;
        return count;
    }

    abstract double calculateSalary();

    double calculateBonus() {
        int present = getPresentDays();
        if (present > 25) return baseSalary * 0.1;
        else if (present > 20) return baseSalary * 0.05;
        else return 0;
    }

    void generatePaySlip() {
        double salary = calculateSalary();
        double bonus = calculateBonus();
        totalSalaryExpense += (salary + bonus);
        System.out.println("PaySlip for " + empName + " (" + designation + ")");
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Attendance Days: " + getPresentDays());
        System.out.println("Bonus: " + bonus);
        System.out.println("Net Salary: " + (salary + bonus));
        System.out.println("\n");
    }

    void requestLeave(int days) {
        System.out.println(empName + " requested " + days + " days leave.");
    }
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String id, String name, String dept, String desig, double sal, String join)
    { super(id, name, dept, desig, sal, join); }

    double calculateSalary() {
        return baseSalary;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String id, String name, String dept, String desig, double sal, String join)
    { super(id, name, dept, desig, sal, join); }

    double calculateSalary() {
        return baseSalary * (getPresentDays() / (double) workingDaysPerMonth);
    }
}

class ContractEmployee extends Employee {
    ContractEmployee(String id, String name, String dept, String desig, double sal, String join)
    { super(id, name, dept, desig, sal, join); }

    double calculateSalary() {
        return baseSalary * 0.9;
    }
}

class Department {
    String deptId, deptName;
    Employee manager;
    Employee[] employees;
    double budget;

    Department(String deptId, String deptName, Employee manager, Employee[] employees, double budget) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.manager = manager;
        this.employees = employees;
        this.budget = budget;
    }
}

public class PayrollSystem {
    static void calculateCompanyPayroll(Employee[] employees) {
        double total = 0;
        for (Employee e : employees) {
            total += e.calculateSalary() + e.calculateBonus();
        }
        System.out.println("Total Company Payroll Expense: " + total);
    }

    static void getDepartmentWiseExpenses(Department[] depts) {
        for (Department d : depts) {
            double sum = 0;
            for (Employee e : d.employees) {
                sum += e.calculateSalary() + e.calculateBonus();
            }
            System.out.println("Department: " + d.deptName + ", Expense: " + sum);
        }
    }

    static void getAttendanceReport(Employee[] employees) {
        for (Employee e : employees) {
            System.out.println(e.empName + " attended " + e.getPresentDays() + " days.");
        }
    }

    public static void main(String[] args) {
        Employee e1 = new FullTimeEmployee("E001", "Alice", "IT", "Developer", 50000, "2021-06-01");
        Employee e2 = new PartTimeEmployee("E002", "Bob", "HR", "Recruiter", 30000, "2022-01-15");
        Employee e3 = new ContractEmployee("E003", "Charlie", "Finance", "Analyst", 40000, "2023-03-12");

        for (int i = 1; i <= 26; i++) e1.markAttendance(i, true);
        for (int i = 1; i <= 20; i++) e2.markAttendance(i, true);
        for (int i = 1; i <= 28; i++) e3.markAttendance(i, true);

        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        Department d1 = new Department("D001", "IT", e1, new Employee[]{e1}, 200000);
        Department d2 = new Department("D002", "HR", e2, new Employee[]{e2}, 150000);
        Department d3 = new Department("D003", "Finance", e3, new Employee[]{e3}, 180000);

        Employee[] employees = {e1, e2, e3};
        Department[] departments = {d1, d2, d3};

        calculateCompanyPayroll(employees);
        getDepartmentWiseExpenses(departments);
        getAttendanceReport(employees);

        System.out.println("Total Employees: " + Employee.totalEmployees);
        System.out.println("Company Name: " + Employee.companyName);
    }
}
