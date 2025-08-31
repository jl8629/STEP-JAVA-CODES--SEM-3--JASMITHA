import java.util.*;

class Book {
    String bookId, title, author, isbn, category;
    boolean isIssued;
    String issueDate, dueDate;

    static int totalBooks = 0;

    Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        totalBooks++;
    }

    void displayBook() {
        System.out.println("Book ID: " + bookId + ", Title: " + title + 
                           ", Author: " + author + ", Category: " + category + 
                           ", Issued: " + isIssued);
    }
}

class Member {
    String memberId, memberName, memberType, membershipDate;
    Book[] booksIssued;
    double totalFines = 0;
    int maxBooks;

    static int totalMembers = 0;
    static String libraryName = "City Library";
    static double finePerDay = 10.0;
    static int maxBooksAllowed = 5;

    Member(String memberId, String memberName, String memberType, String membershipDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberType = memberType;
        this.membershipDate = membershipDate;
        this.booksIssued = new Book[maxBooksAllowed];
        this.maxBooks = setMaxBooks(memberType);
        totalMembers++;
    }

    int setMaxBooks(String type) {
        if (type.equalsIgnoreCase("Student")) return 3;
        else if (type.equalsIgnoreCase("Faculty")) return 7;
        else return 5; // General
    }

    void issueBook(Book book, String issueDate, String dueDate) {
        if (book.isIssued) {
            System.out.println("Book already issued!");
            return;
        }
        int count = getIssuedCount();
        if (count >= maxBooks) {
            System.out.println("Cannot issue more books! Limit reached.");
            return;
        }
        booksIssued[count] = book;
        book.isIssued = true;
        book.issueDate = issueDate;
        book.dueDate = dueDate;
        System.out.println(memberName + " issued book: " + book.title);
    }

    void returnBook(String bookId, String returnDate) {
        for (int i = 0; i < booksIssued.length; i++) {
            if (booksIssued[i] != null && booksIssued[i].bookId.equals(bookId)) {
                Book book = booksIssued[i];
                int overdueDays = calculateOverdueDays(book.dueDate, returnDate);
                if (overdueDays > 0) {
                    double fine = overdueDays * finePerDay;
                    totalFines += fine;
                    System.out.println("Fine added: Rs." + fine);
                }
                book.isIssued = false;
                booksIssued[i] = null;
                System.out.println("Book returned: " + book.title);
                return;
            }
        }
        System.out.println("Book not found in issued list.");
    }

    void renewBook(String bookId, String newDueDate) {
        for (Book book : booksIssued) {
            if (book != null && book.bookId.equals(bookId)) {
                book.dueDate = newDueDate;
                System.out.println("Book renewed: " + book.title + ", new due date: " + newDueDate);
                return;
            }
        }
        System.out.println("Book not found to renew.");
    }

    int calculateOverdueDays(String dueDate, String returnDate) {
        int due = Integer.parseInt(dueDate);
        int ret = Integer.parseInt(returnDate);
        return Math.max(0, ret - due);
    }

    int getIssuedCount() {
        int count = 0;
        for (Book b : booksIssued) {
            if (b != null) count++;
        }
        return count;
    }

    void displayMember() {
        System.out.println("Member: " + memberName + " (" + memberType + 
                           "), Books Issued: " + getIssuedCount() + 
                           ", Total Fines: Rs." + totalFines);
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        // Create some books
        Book b1 = new Book("B001", "Java Basics", "James Gosling", "ISBN001", "Programming");
        Book b2 = new Book("B002", "Data Structures", "Robert Lafore", "ISBN002", "CS");
        Book b3 = new Book("B003", "History of India", "Romila Thapar", "ISBN003", "History");

        Member m1 = new Member("M001", "Alice", "Student", "2023-01-10");
        Member m2 = new Member("M002", "Bob", "Faculty", "2022-09-05");

        m1.issueBook(b1, "01", "05");
        m1.issueBook(b2, "01", "05");
        m1.displayMember();

        m1.returnBook("B001", "07"); // late return -> fine
        m1.displayMember();

        m2.issueBook(b3, "01", "10");
        m2.displayMember();

        System.out.println("\nTotal Books: " + Book.totalBooks);
        System.out.println("Total Members: " + Member.totalMembers);
        System.out.println("Library Name: " + Member.libraryName);
    }
}
