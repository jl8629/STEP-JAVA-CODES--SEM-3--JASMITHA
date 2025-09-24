class HotelBooking {
    void calculatePrice(String roomType, int nights) {
        double pricePerNight = getRoomPrice(roomType);
        double total = pricePerNight * nights;
        System.out.println("Standard Booking:");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Price per Night: $" + pricePerNight);
        System.out.println("Total Cost: $" + total);
        System.out.println();
    }

    void calculatePrice(String roomType, int nights, double seasonalMultiplier) {
        double pricePerNight = getRoomPrice(roomType);
        double total = pricePerNight * nights * seasonalMultiplier;
        System.out.println("Seasonal Booking:");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Seasonal Multiplier: " + seasonalMultiplier);
        System.out.println("Price per Night: $" + pricePerNight);
        System.out.println("Total Cost: $" + total);
        System.out.println();
    }

    void calculatePrice(String roomType, int nights, double corporateDiscount, boolean mealPackage) {
        double pricePerNight = getRoomPrice(roomType);
        double total = pricePerNight * nights;
        double discount = total * corporateDiscount;
        total -= discount;
        if(mealPackage) total += 50 * nights;
        System.out.println("Corporate Booking:");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Corporate Discount: $" + discount);
        if(mealPackage) System.out.println("Meal Package: Included ($50 per night)");
        System.out.println("Total Cost: $" + total);
        System.out.println();
    }

    void calculatePrice(String roomType, int nights, int guestCount, double decorationFee, boolean catering) {
        double pricePerNight = getRoomPrice(roomType);
        double total = pricePerNight * nights;
        total += decorationFee;
        if(catering) total += guestCount * 30;
        System.out.println("Wedding Package:");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Guest Count: " + guestCount);
        System.out.println("Decoration Fee: $" + decorationFee);
        if(catering) System.out.println("Catering Included: $" + (guestCount * 30));
        System.out.println("Total Cost: $" + total);
        System.out.println();
    }

    double getRoomPrice(String roomType) {
        switch(roomType.toLowerCase()) {
            case "single": return 100;
            case "double": return 180;
            case "suite": return 300;
            default: return 150;
        }
    }

    public static void main(String[] args) {
        HotelBooking booking = new HotelBooking();
        booking.calculatePrice("Single", 3);
        booking.calculatePrice("Double", 4, 1.2);
        booking.calculatePrice("Suite", 5, 0.1, true);
        booking.calculatePrice("Double", 2, 50, 200, true);
    }
}
