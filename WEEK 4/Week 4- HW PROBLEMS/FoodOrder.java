public class FoodOrder {
    String customerName;
    String foodItem;
    int quantity;
    double price;
    static final double FIXED_RATE = 150;

    public FoodOrder() {
        this("Unknown", "Unknown", 0, 0);
    }

    public FoodOrder(String foodItem) {
        this("Customer", foodItem, 1, FIXED_RATE);
    }

    public FoodOrder(String foodItem, int quantity) {
        this("Customer", foodItem, quantity, quantity * FIXED_RATE);
    }

    public FoodOrder(String customerName, String foodItem, int quantity, double price) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    public void printBill() {
        System.out.println("Customer: " + customerName + " | Food: " + foodItem + " | Quantity: " + quantity + " | Total: ₹" + price);
    }

    public static void main(String[] args) {
        FoodOrder o1 = new FoodOrder();
        FoodOrder o2 = new FoodOrder("Burger");
        FoodOrder o3 = new FoodOrder("Pizza", 3);
        FoodOrder o4 = new FoodOrder("Alice", "Pasta", 2, 400);

        o1.printBill();
        o2.printBill();
        o3.printBill();
        o4.printBill();
    }
}
