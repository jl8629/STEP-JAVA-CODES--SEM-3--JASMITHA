class University {
    private String uniName;

    public University(String uniName) {
        this.uniName = uniName;
    }

    class Department {
        private String deptName;

        public Department(String deptName) {
            this.deptName = deptName;
        }

        void showDepartment() {
            System.out.println("University: " + uniName + ", Department: " + deptName);
        }
    }

    static class ExamCell {
        static void conductExam() {
            System.out.println("Exams are being conducted by the Exam Cell.");
        }
    }
}

public class UniversityDemo {
    public static void main(String[] args) {
        University uni = new University("Global University");
        University.Department dept = uni.new Department("Computer Science");
        dept.showDepartment();

        University.ExamCell.conductExam();
    }
}
