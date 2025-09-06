public class MovieTicket {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;

    public MovieTicket() {
        this("Unknown", "NA", 0, 0.0);
    }

    public MovieTicket(String movieName) {
        this(movieName, "NA", 0, 200.0);
    }

    public MovieTicket(String movieName, int seatNumber) {
        this(movieName, "PVR", seatNumber, 200.0);
    }

    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public void printTicket() {
        System.out.println("Movie: " + movieName + " | Theatre: " + theatreName + " | Seat: " + seatNumber + " | Price: " + price);
    }

    public static void main(String[] args) {
        MovieTicket t1 = new MovieTicket();
        MovieTicket t2 = new MovieTicket("Inception");
        MovieTicket t3 = new MovieTicket("Avengers", 12);
        MovieTicket t4 = new MovieTicket("Interstellar", "IMAX", 45, 350.0);

        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}
