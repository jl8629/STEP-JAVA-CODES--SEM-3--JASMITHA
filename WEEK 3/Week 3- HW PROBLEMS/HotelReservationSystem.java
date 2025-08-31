import java.util.*;

class Room {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isAvailable;
    private int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.maxOccupancy = maxOccupancy;
        this.isAvailable = true;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public int getMaxOccupancy() { return maxOccupancy; }

    public void displayRoomInfo() {
        System.out.println("Room " + roomNumber + " | " + roomType + " | Price: " + pricePerNight +
                           " | Max Occupancy: " + maxOccupancy + " | Available: " + isAvailable);
    }
}

class Guest {
    private String guestId;
    private String guestName;
    private String phoneNumber;
    private String email;
    private String[] bookingHistory;
    private int bookingCount;

    public Guest(String guestId, String guestName, String phoneNumber, String email) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new String[10];
        this.bookingCount = 0;
    }

    public String getGuestId() { return guestId; }
    public String getGuestName() { return guestName; }

    public void addBookingHistory(String bookingId) {
        if (bookingCount < bookingHistory.length) {
            bookingHistory[bookingCount++] = bookingId;
        }
    }

    public void displayGuestInfo() {
        System.out.println("Guest ID: " + guestId + " | Name: " + guestName + 
                           " | Phone: " + phoneNumber + " | Email: " + email);
    }
}

class Booking {
    private String bookingId;
    private Guest guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private double totalAmount;

    static int totalBookings = 0;
    static double hotelRevenue = 0.0;
    static String hotelName = "DreamStay Hotels";

    public Booking(String bookingId, Guest guest, Room room, String checkInDate, String checkOutDate, int nights) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = room.getPricePerNight() * nights;
        totalBookings++;
        hotelRevenue += totalAmount;
        room.setAvailable(false);
        guest.addBookingHistory(bookingId);
    }

    public static double getTotalRevenue() { return hotelRevenue; }
    public static int getTotalBookings() { return totalBookings; }

    public void cancelReservation() {
        System.out.println("Booking " + bookingId + " cancelled.");
        room.setAvailable(true);
        totalBookings--;
        hotelRevenue -= totalAmount;
    }

    public void displayBookingInfo() {
        System.out.println("Booking ID: " + bookingId + " | Guest: " + guest.getGuestName() +
                           " | Room: " + room.getRoomNumber() + " (" + room.getRoomType() + ")" +
                           " | From: " + checkInDate + " To: " + checkOutDate +
                           " | Amount: " + totalAmount);
    }

    public static double getOccupancyRate(Room[] rooms) {
        int occupied = 0;
        for (Room r : rooms) {
            if (!r.isAvailable()) occupied++;
        }
        return (double) occupied / rooms.length * 100;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Room[] rooms = {
            new Room("101", "Single", 2000, 1),
            new Room("102", "Double", 3500, 2),
            new Room("201", "Suite", 5000, 4),
            new Room("202", "Double", 3500, 2)
        };

        Guest g1 = new Guest("G001", "Alice", "9876543210", "alice@mail.com");
        Guest g2 = new Guest("G002", "Bob", "9123456780", "bob@mail.com");

        Booking b1 = new Booking("B001", g1, rooms[0], "2025-09-01", "2025-09-03", 2);
        Booking b2 = new Booking("B002", g2, rooms[1], "2025-09-02", "2025-09-05", 3);

        System.out.println("\n--- Hotel Reservation System ---");
        for (Room r : rooms) r.displayRoomInfo();

        System.out.println("\n--- Bookings ---");
        b1.displayBookingInfo();
        b2.displayBookingInfo();

        System.out.println("\n--- Guests ---");
        g1.displayGuestInfo();
        g2.displayGuestInfo();

        System.out.println("\n--- Reports ---");
        System.out.println("Hotel: " + Booking.hotelName);
        System.out.println("Total Bookings: " + Booking.getTotalBookings());
        System.out.println("Total Revenue: " + Booking.getTotalRevenue());
        System.out.println("Occupancy Rate: " + Booking.getOccupancyRate(rooms) + "%");
    }
}
