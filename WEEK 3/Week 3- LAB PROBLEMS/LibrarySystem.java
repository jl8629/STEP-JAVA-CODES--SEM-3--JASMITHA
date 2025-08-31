import java.util.Scanner;

class Book {
    private String bookId, title, author;
    private boolean isAvailable;
    private static int total=0, available=0, count=1;
    public Book(String title,String author){
        this.bookId="B"+count++;
        this.title=title;
        this.author=author;
        this.isAvailable=true;
        total++; available++;
    }
    public String getId(){return bookId;}
    public boolean isAvailable(){return isAvailable;}
    public void issue(){if(isAvailable){isAvailable=false;available--;}}
    public void giveBack(){if(!isAvailable){isAvailable=true;available++;}}
    public void display(){System.out.println(bookId+" "+title+" "+author+" "+isAvailable);}
    public static int getTotal(){return total;}
    public static int getAvailable(){return available;}
}

class Member {
    private String memberId,name;
    private String[] issued;
    private int count=0;
    private static int idc=1;
    public Member(String name,int max){
        this.memberId="M"+idc++;
        this.name=name;
        issued=new String[max];
    }
    public void borrow(Book b){
        if(b.isAvailable()&&count<issued.length){
            b.issue(); issued[count++]=b.getId();
            System.out.println(name+" borrowed "+b.getId());
        } else System.out.println(name+" cannot borrow "+b.getId());
    }
    public void returnBook(String id,Book[] books){
        for(int i=0;i<count;i++){
            if(issued[i].equals(id)){
                for(Book b:books) if(b.getId().equals(id)) b.giveBack();
                System.out.println(name+" returned "+id);
                issued[i]=issued[--count]; issued[count]=null;
                break;
            }
        }
    }
    public void display(){
        System.out.print(memberId+" "+name+" Books:");
        for(int i=0;i<count;i++) System.out.print(" "+issued[i]);
        System.out.println();
    }
}

public class LibrarySystem {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n=sc.nextInt();
        Book[] books=new Book[n];
        for(int i=0;i<n;i++){
            System.out.print("Enter title of book "+(i+1)+": ");
            String title=sc.next();
            System.out.print("Enter author of book "+(i+1)+": ");
            String author=sc.next();
            books[i]=new Book(title,author);
        }

        System.out.print("Enter number of members: ");
        int m=sc.nextInt();
        Member[] mem=new Member[m];
        for(int i=0;i<m;i++){
            System.out.print("Enter name of member "+(i+1)+": ");
            String name=sc.next();
            mem[i]=new Member(name,3);
        }

        mem[0].borrow(books[0]);
        mem[0].borrow(books[1]);
        mem[1].borrow(books[1]);
        mem[0].returnBook(books[0].getId(),books);
        mem[1].borrow(books[0]);

        System.out.println("\nBooks Info:");
        for(Book b:books) b.display();
        System.out.println("\nMembers Info:");
        for(Member x:mem) x.display();
        System.out.println("\nTotal:"+Book.getTotal()+" Available:"+Book.getAvailable());

        sc.close();
    }
}
