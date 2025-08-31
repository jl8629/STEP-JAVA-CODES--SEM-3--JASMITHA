import java.util.Scanner;

class Subject {
    private String subjectCode, subjectName;
    private int maxMarks, passMarks;
    public Subject(String code,String name,int max,int pass){
        subjectCode=code; subjectName=name; maxMarks=max; passMarks=pass;
    }
    public String getCode(){return subjectCode;}
    public String getName(){return subjectName;}
    public int getPassMarks(){return passMarks;}
    public int getMaxMarks(){return maxMarks;}
}

class Student {
    private String studentId, studentName;
    private int grade;
    private double[] marks=new double[5];
    private double totalMarks, percentage;
    private static int idc=1;

    public Student(String name,int grade){
        this.studentId="S"+idc++;
        this.studentName=name;
        this.grade=grade;
    }

    public void setMark(int index,double mark){marks[index]=mark;}
    public void calculateTotal(){totalMarks=0; for(double m:marks) totalMarks+=m;}
    public void calculatePercentage(){percentage=totalMarks/5;}
    public boolean isPass(Subject[] subs){
        for(int i=0;i<5;i++) if(marks[i]<subs[i].getPassMarks()) return false;
        return true;
    }
    public void displayResult(Subject[] subs){
        calculateTotal(); calculatePercentage();
        System.out.println(studentId+" "+studentName+" Grade:"+grade+" Total:"+totalMarks+" %:"+percentage+" Pass:"+isPass(subs));
    }
    public double getPercentage(){return percentage;}
    public String getName(){return studentName;}

    public static Student getTopStudent(Student[] st){
        Student top=st[0];
        for(Student s:st) if(s.percentage>top.percentage) top=s;
        return top;
    }
    public static double getClassAverage(Student[] st){
        double sum=0; for(Student s:st) sum+=s.percentage; return sum/st.length;
    }
    public static double getPassPercentage(Student[] st,Subject[] subs){
        int pass=0;
        for(Student s:st) if(s.isPass(subs)) pass++;
        return (pass*100.0)/st.length;
    }
}

class Teacher {
    private String teacherId, teacherName, subject;
    private int studentsHandled;
    private static int idc=1,totalTeachers=0;

    public Teacher(String name,String subject){
        teacherId="T"+idc++;
        teacherName=name;
        this.subject=subject;
        totalTeachers++;
    }
    public void assignGrades(Student s,Subject subj,double mark,int index){
        s.setMark(index,mark);
        studentsHandled++;
        System.out.println(teacherName+" assigned "+mark+" in "+subj.getName()+" to "+s.getName());
    }
    public void displayTeacherInfo(){
        System.out.println(teacherId+" "+teacherName+" Subject:"+subject+" StudentsHandled:"+studentsHandled);
    }
    public static int getTotalTeachers(){return totalTeachers;}
}

public class SchoolSystem {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        Subject[] subs=new Subject[5];
        subs[0]=new Subject("M01","Math",100,40);
        subs[1]=new Subject("S01","Science",100,40);
        subs[2]=new Subject("E01","English",100,40);
        subs[3]=new Subject("H01","History",100,40);
        subs[4]=new Subject("C01","Computer",100,40);

        System.out.print("Enter number of students: ");
        int n=sc.nextInt();
        Student[] students=new Student[n];
        for(int i=0;i<n;i++){
            System.out.print("Enter student name: ");
            String name=sc.next();
            System.out.print("Enter grade: ");
            int g=sc.nextInt();
            students[i]=new Student(name,g);
        }

        System.out.print("Enter number of teachers: ");
        int t=sc.nextInt();
        Teacher[] teachers=new Teacher[t];
        for(int i=0;i<t;i++){
            System.out.print("Enter teacher name: ");
            String name=sc.next();
            System.out.print("Enter subject: ");
            String sub=sc.next();
            teachers[i]=new Teacher(name,sub);
        }

        teachers[0].assignGrades(students[0],subs[0],85,0);
        teachers[1].assignGrades(students[0],subs[1],75,1);
        teachers[0].assignGrades(students[1],subs[0],45,0);
        teachers[1].assignGrades(students[1],subs[1],35,1);

        System.out.println("\nResults:");
        for(Student s:students) s.displayResult(subs);

        System.out.println("\nTeachers Info:");
        for(Teacher th:teachers) th.displayTeacherInfo();

        System.out.println("\nTop Student: "+Student.getTopStudent(students).getName());
        System.out.println("Class Average: "+Student.getClassAverage(students));
        System.out.println("Pass Percentage: "+Student.getPassPercentage(students,subs));
        System.out.println("Total Teachers: "+Teacher.getTotalTeachers());

        sc.close();
    }
}
