import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LibraryUser {
    String name;
    String id;
    LocalDateTime entryTime;
    LibraryUser(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public void entryLog() {
        entryTime = LocalDateTime.now();
        System.out.println(name + " (" + id + ") entered at " + entryTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    public void displayInfo() {
        System.out.println("User: " + name + " | ID: " + id + " | Role: " + getRole());
    }
    public String getRole() {
        return "LibraryUser";
    }
}

class Student extends LibraryUser {
    int borrowedCount;
    Student(String name, String id) {
        super(name, id);
        this.borrowedCount = 0;
    }
    public void borrowBook(String title) {
        borrowedCount++;
        System.out.println(name + " borrowed \"" + title + "\". Total borrowed: " + borrowedCount);
    }
    public void accessComputers() {
        System.out.println(name + " accessed the student computer lab.");
    }
    @Override
    public String getRole() {
        return "Student";
    }
}

class Faculty extends LibraryUser {
    Faculty(String name, String id) {
        super(name, id);
    }
    public void reserveBook(String title) {
        System.out.println(name + " reserved \"" + title + "\" for pickup.");
    }
    public void accessResearchDB() {
        System.out.println(name + " accessed the research databases.");
    }
    @Override
    public String getRole() {
        return "Faculty";
    }
}

class Guest extends LibraryUser {
    Guest(String name, String id) {
        super(name, id);
    }
    public void browseBooks() {
        System.out.println(name + " is browsing books in the library.");
    }
    @Override
    public String getRole() {
        return "Guest";
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        LibraryUser[] users = new LibraryUser[] {
            new Student("Jane", "S1001"),
            new Faculty("Dr. Rao", "F2001"),
            new Guest("Visitor", "G3001"),
            new Student("Sam", "S1002")
        };
        for (LibraryUser u : users) {
            u.entryLog();
            u.displayInfo();
        }
        System.out.println("Performing role-specific operations:");
        for (LibraryUser u : users) {
            if (u instanceof Student) {
                Student s = (Student) u;
                s.borrowBook("Introduction to Algorithms");
                s.accessComputers();
            } else if (u instanceof Faculty) {
                Faculty f = (Faculty) u;
                f.reserveBook("Advanced Research Methods");
                f.accessResearchDB();
            } else if (u instanceof Guest) {
                Guest g = (Guest) u;
                g.browseBooks();
            }
        }
    }
}
