class Course {
    protected String title;
    protected String instructor;
    protected String enrollmentDate;

    Course(String title, String instructor, String enrollmentDate) {
        this.title = title;
        this.instructor = instructor;
        this.enrollmentDate = enrollmentDate;
    }

    void showProgress() {
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Progress: Not available");
        System.out.println();
    }
}

class VideoCourse extends Course {
    private double completionPercentage;
    private int watchTime; 

    VideoCourse(String title, String instructor, String enrollmentDate, double completionPercentage, int watchTime) {
        super(title, instructor, enrollmentDate);
        this.completionPercentage = completionPercentage;
        this.watchTime = watchTime;
    }

    @Override
    void showProgress() {
        System.out.println("Video Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Completion: " + completionPercentage + "%");
        System.out.println("Watch Time: " + watchTime + " minutes");
        System.out.println();
    }
}

class InteractiveCourse extends Course {
    private int quizScore;
    private int projectsCompleted;

    InteractiveCourse(String title, String instructor, String enrollmentDate, int quizScore, int projectsCompleted) {
        super(title, instructor, enrollmentDate);
        this.quizScore = quizScore;
        this.projectsCompleted = projectsCompleted;
    }

    @Override
    void showProgress() {
        System.out.println("Interactive Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Quiz Score: " + quizScore + "%");
        System.out.println("Projects Completed: " + projectsCompleted);
        System.out.println();
    }
}

class ReadingCourse extends Course {
    private int pagesRead;
    private int notesTaken;

    ReadingCourse(String title, String instructor, String enrollmentDate, int pagesRead, int notesTaken) {
        super(title, instructor, enrollmentDate);
        this.pagesRead = pagesRead;
        this.notesTaken = notesTaken;
    }

    @Override
    void showProgress() {
        System.out.println("Reading Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Pages Read: " + pagesRead);
        System.out.println("Notes Taken: " + notesTaken);
        System.out.println();
    }
}

class CertificationCourse extends Course {
    private int examAttempts;
    private boolean certified;

    CertificationCourse(String title, String instructor, String enrollmentDate, int examAttempts, boolean certified) {
        super(title, instructor, enrollmentDate);
        this.examAttempts = examAttempts;
        this.certified = certified;
    }

    @Override
    void showProgress() {
        System.out.println("Certification Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Exam Attempts: " + examAttempts);
        System.out.println("Certification Status: " + (certified ? "Certified" : "Not Certified"));
        System.out.println();
    }
}

public class OnlineLearningPlatform {
    public static void main(String[] args) {
        Course video = new VideoCourse("Java Basics", "Alice", "2025-09-01", 75.5, 120);
        Course interactive = new InteractiveCourse("Python Projects", "Bob", "2025-08-15", 88, 5);
        Course reading = new ReadingCourse("Data Structures", "Carol", "2025-07-20", 200, 30);
        Course cert = new CertificationCourse("AWS Certification", "David", "2025-06-10", 2, true);

        video.showProgress();
        interactive.showProgress();
        reading.showProgress();
        cert.showProgress();
    }
}
