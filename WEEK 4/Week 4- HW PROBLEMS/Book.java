public class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;

    public Book() {
        this("Unknown", "Unknown", "0000", true);
    }

    public Book(String title, String author) {
        this(title, author, "0000", true);
    }

    public Book(String title, String author, String isbn) {
        this(title, author, isbn, true);
    }

    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " borrowed successfully.");
        } else {
            System.out.println(title + " is not available.");
        }
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println(title + " returned successfully.");
    }

    public void displayBookInfo() {
        System.out.println("Title: " + title + " | Author: " + author + " | ISBN: " + isbn + " | Available: " + (isAvailable ? "Yes" : "No"));
    }

    public static void main(String[] args) {
        Book b1 = new Book();
        Book b2 = new Book("The Hobbit", "J.R.R. Tolkien");
        Book b3 = new Book("1984", "George Orwell", "12345");

        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();

        b2.borrowBook();
        b2.displayBookInfo();

        b2.returnBook();
        b2.displayBookInfo();
    }
}
