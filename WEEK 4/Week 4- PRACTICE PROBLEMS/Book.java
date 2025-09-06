public class Book {
    String title;
    String author;
    double price;

    Book() {
        title = "Unknown Title";
        author = "Unknown Author";
        price = 0.0;
    }

    Book(String t, String a, double p) {
        title = t;
        author = a;
        price = p;
    }

    void display() {
        System.out.println("Title: " + title + ", Author: " + author + ", Price: " + price);
    }

    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book("Java Programming", "James Gosling", 499.99);
        book1.display();
        book2.display();
    }
}
