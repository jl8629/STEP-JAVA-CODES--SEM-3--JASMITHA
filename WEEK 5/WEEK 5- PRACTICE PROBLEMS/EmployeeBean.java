import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.lang.reflect.Method;

public class EmployeeBean implements Serializable {
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean isActive;

    public EmployeeBean() {
    }

    public EmployeeBean(String employeeId, String firstName, String lastName, double salary, String department, Date hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        setSalary(salary);
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        if (employeeId != null && !employeeId.isEmpty()) {
            this.employeeId = employeeId;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.isEmpty()) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) {
            this.lastName = lastName;
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null && !department.isEmpty()) {
            this.department = department;
        }
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getYearsOfService() {
        if (hireDate == null) return 0;
        LocalDate hireLocal = hireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(hireLocal, LocalDate.now()).getYears();
    }

    public String getFormattedSalary() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(salary);
    }

    public void setFullName(String fullName) {
        if (fullName != null && fullName.contains(" ")) {
            String[] parts = fullName.split(" ", 2);
            this.firstName = parts[0];
            this.lastName = parts[1];
        }
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", salary=" + getFormattedSalary() +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                ", yearsOfService=" + getYearsOfService() +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeBean)) return false;
        EmployeeBean that = (EmployeeBean) o;
        return Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    public static void main(String[] args) {
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFirstName("Alice");
        emp1.setLastName("Johnson");
        emp1.setSalary(60000);
        emp1.setDepartment("IT");
        emp1.setHireDate(new Date(120, 0, 15));
        emp1.setActive(true);

        EmployeeBean emp2 = new EmployeeBean("E002", "Bob", "Smith", 75000, "Finance", new Date(118, 5, 10), true);

        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(emp1.getFullName());
        System.out.println(emp2.getYearsOfService());
        System.out.println(emp1.getFormattedSalary());

        emp1.setFullName("Alice Cooper");
        emp1.setSalary(-5000);

        EmployeeBean[] employees = {emp1, emp2};
        Arrays.sort(employees, (a, b) -> Double.compare(a.getSalary(), b.getSalary()));
        for (EmployeeBean e : employees) System.out.println(e);

        Arrays.stream(employees).filter(EmployeeBean::isActive).forEach(System.out::println);

        EmployeeBean emp3 = new EmployeeBean();
        JavaBeanProcessor.copyProperties(emp2, emp3);
        JavaBeanProcessor.printAllProperties(emp3);
    }
}

class JavaBeanProcessor {
    public static void printAllProperties(EmployeeBean emp) {
        try {
            Method[] methods = emp.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
                    Object value = m.invoke(emp);
                    System.out.println(m.getName() + " = " + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyProperties(EmployeeBean source, EmployeeBean target) {
        try {
            Method[] methods = source.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
                    String propName = m.getName().startsWith("get") ? m.getName().substring(3) : m.getName().substring(2);
                    Object value = m.invoke(source);
                    try {
                        Method setter = target.getClass().getMethod("set" + propName, m.getReturnType());
                        setter.invoke(target, value);
                    } catch (NoSuchMethodException ignored) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
