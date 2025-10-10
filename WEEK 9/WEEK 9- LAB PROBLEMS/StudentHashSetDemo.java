import java.util.HashSet;
import java.util.Objects;

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student [ID: " + id + ", Name: " + name + "]";
    }
}

public class StudentHashSetDemo {
    public static void main(String[] args) {
        HashSet<Student> students = new HashSet<>();

        Student s1 = new Student(101, "Alice");
        Student s2 = new Student(102, "Bob");
        Student s3 = new Student(101, "Alice");

        students.add(s1);
        students.add(s2);
        students.add(s3);

        System.out.println("Students in HashSet:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
