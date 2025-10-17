import java.util.*;

public class Library {
    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book '" + book.getTitle() + "' to " + name + " Library");
    }

    public void showBooks() {
        System.out.println("Books in " + name + " Library:");
        for (Book book : books) {
            book.showDetails();
        }
    }
}
