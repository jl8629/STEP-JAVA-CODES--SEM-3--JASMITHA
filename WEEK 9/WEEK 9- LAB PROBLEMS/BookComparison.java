class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        return this.title.equals(other.title) && this.author.equals(other.author);
    }

    @Override
    public String toString() {
        return "Book [Title: " + title + ", Author: " + author + "]";
    }
}

public class BookComparison {
    public static void main(String[] args) {
        Book b1 = new Book("Java Programming", "James Gosling");
        Book b2 = new Book("Java Programming", "James Gosling");
        Book b3 = b1;

        System.out.println("b1 == b2 : " + (b1 == b2));
        System.out.println("b1.equals(b2) : " + b1.equals(b2));
        System.out.println("b1 == b3 : " + (b1 == b3));
        System.out.println("b1.equals(b3) : " + b1.equals(b3));

        System.out.println("\nBook 1: " + b1);
        System.out.println("Book 2: " + b2);
        System.out.println("Book 3: " + b3);
    }
}
