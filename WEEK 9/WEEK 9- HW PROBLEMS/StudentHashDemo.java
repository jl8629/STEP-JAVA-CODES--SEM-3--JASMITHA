import java.util.HashSet;
import java.util.Objects;

class Student {
    private int rollNo;
    private String name;

    public Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return this.rollNo == other.rollNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo);
    }

    @Override
    public String toString() {
        return "Student [Roll No: " + rollNo + ", Name: " + name + "]";
    }
}

public class StudentHashDemo {
    public static void main(String[] args) {
        HashSet<Student> set = new HashSet<>();

        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(2, "Bob");
        Student s3 = new Student(1, "Alice");

        set.add(s1);
        set.add(s2);
        set.add(s3);

        System.out.println("Students in HashSet:");
        for (Student s : set) {
            System.out.println(s);
        }
    }
}
