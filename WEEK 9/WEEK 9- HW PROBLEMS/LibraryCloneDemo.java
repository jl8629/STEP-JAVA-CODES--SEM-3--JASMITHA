import java.util.ArrayList;
import java.util.List;

class Book implements Cloneable {
    String title;
    String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "[Title: " + title + ", Author: " + author + "]";
    }
}

class Library implements Cloneable {
    List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book b) {
        books.add(b);
    }

    protected Object shallowClone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected Object deepClone() throws CloneNotSupportedException {
        Library cloned = (Library) super.clone();
        cloned.books = new ArrayList<>();
        for (Book b : this.books) {
            cloned.books.add((Book) b.clone());
        }
        return cloned;
    }

    @Override
    public String toString() {
        return books.toString();
    }
}

public class LibraryCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Library lib1 = new Library();
        lib1.addBook(new Book("Java Basics", "Alice"));
        lib1.addBook(new Book("Python Essentials", "Bob"));

        Library shallowLib = (Library) lib1.shallowClone();
        Library deepLib = (Library) lib1.deepClone();

        System.out.println("Original Library: " + lib1);
        System.out.println("Shallow Cloned Library: " + shallowLib);
        System.out.println("Deep Cloned Library: " + deepLib);

        shallowLib.books.get(0).title = "Advanced Java";
        deepLib.books.get(1).title = "Advanced Python";

        System.out.println("\nAfter modifying cloned libraries:");
        System.out.println("Original Library: " + lib1);
        System.out.println("Shallow Cloned Library: " + shallowLib);
        System.out.println("Deep Cloned Library: " + deepLib);
    }
}
