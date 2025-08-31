import java.util.*;

class Subject {
    String subjectCode;
    String subjectName;
    int credits;
    String instructor;

    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }
}

class Student {
    String studentId;
    String studentName;
    String className;
    String[] subjects;
    double[][] marks; 
    double gpa;

    static int totalStudents = 0;
    static String schoolName = "Springfield High School";
    static String[] gradingScale = {"A", "B", "C", "D", "F"};
    static double passPercentage = 40.0;

    public Student(String studentId, String studentName, String className, String[] subjects, int testsPerSubject) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects;
        this.marks = new double[subjects.length][testsPerSubject];
        this.gpa = 0.0;
        totalStudents++;
    }

    public void addMarks(String subject, double[] testMarks) {
        int index = -1;
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equals(subject)) {
                index = i;
                break;
            }
        }
        if (index != -1 && testMarks.length == marks[index].length) {
            marks[index] = testMarks;
        } else {
            System.out.println("Invalid subject or marks length mismatch for " + subject);
        }
    }

    public void calculateGPA() {
        double totalPercentage = 0.0;
        int subjectCount = subjects.length;

        for (int i = 0; i < subjects.length; i++) {
            double sum = 0;
            for (double m : marks[i]) {
                sum += m;
            }
            double avg = sum / marks[i].length;
            totalPercentage += avg;
        }
        double overallPercentage = totalPercentage / subjectCount;
        this.gpa = overallPercentage / 20; 
    }

    public void generateReportCard() {
        System.out.println("\n--- Report Card for " + studentName + " (" + studentId + ") ---");
        for (int i = 0; i < subjects.length; i++) {
            double sum = 0;
            for (double m : marks[i]) sum += m;
            double avg = sum / marks[i].length;
            System.out.println(subjects[i] + ": " + avg + "% (" + getGrade(avg) + ")");
        }
        System.out.printf("GPA: %.2f\n", gpa);
        System.out.println("Promotion Eligibility: " + (checkPromotionEligibility() ? "Yes" : "No"));
    }

    public boolean checkPromotionEligibility() {
        for (int i = 0; i < subjects.length; i++) {
            double sum = 0;
            for (double m : marks[i]) sum += m;
            double avg = sum / marks[i].length;
            if (avg < passPercentage) return false;
        }
        return true;
    }

    private String getGrade(double percentage) {
        if (percentage >= 85) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 55) return "C";
        else if (percentage >= 40) return "D";
        else return "F";
    }

    public static void setGradingScale(String[] newScale) {
        gradingScale = newScale;
    }

    public static double calculateClassAverage(Student[] students) {
        double total = 0;
        for (Student s : students) total += s.gpa;
        return total / students.length;
    }

    public static Student[] getTopPerformers(Student[] students, int count) {
        Arrays.sort(students, (a, b) -> Double.compare(b.gpa, a.gpa));
        return Arrays.copyOfRange(students, 0, Math.min(count, students.length));
    }

    public static void generateSchoolReport(Student[] students) {
        System.out.println("\n--- School Report: " + schoolName + " ---");
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Average GPA across school: " + calculateClassAverage(students));
        System.out.println("Top Performers:");
        for (Student s : getTopPerformers(students, 3)) {
            System.out.println(s.studentName + " (" + s.className + ") GPA: " + s.gpa);
        }
    }
}

public class StudentGradeManagementSystem {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Science", "English"};

        Student s1 = new Student("S001", "Alice", "10A", subjects, 3);
        Student s2 = new Student("S002", "Bob", "10A", subjects, 3);
        Student s3 = new Student("S003", "Charlie", "10B", subjects, 3);

        s1.addMarks("Math", new double[]{85, 90, 80});
        s1.addMarks("Science", new double[]{78, 82, 88});
        s1.addMarks("English", new double[]{92, 85, 88});

        s2.addMarks("Math", new double[]{60, 55, 70});
        s2.addMarks("Science", new double[]{68, 72, 65});
        s2.addMarks("English", new double[]{75, 70, 72});

        s3.addMarks("Math", new double[]{40, 45, 50});
        s3.addMarks("Science", new double[]{35, 38, 40});
        s3.addMarks("English", new double[]{60, 58, 62});

        s1.calculateGPA();
        s2.calculateGPA();
        s3.calculateGPA();

        s1.generateReportCard();
        s2.generateReportCard();
        s3.generateReportCard();

        Student[] allStudents = {s1, s2, s3};
        Student.generateSchoolReport(allStudents);
    }
}
