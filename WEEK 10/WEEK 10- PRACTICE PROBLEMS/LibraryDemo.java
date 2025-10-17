public class LibraryDemo {
    public static void main(String[] args) {
        Library lib = new Library("Central City");

        Book b1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565");
        Book b2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084");
        Book b3 = new Book("1984", "George Orwell", "9780451524935");

        lib.addBook(b1);
        lib.addBook(b2);
        lib.addBook(b3);

        System.out.println();
        lib.showBooks();

        Member m1 = new Member("Ravi");

        System.out.println();
        m1.borrowBook(b1);
        m1.borrowBook(b2);

        System.out.println();
        m1.showBorrowedBooks();
    }
}
