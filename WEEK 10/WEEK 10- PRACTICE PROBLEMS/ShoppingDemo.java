public class ShoppingDemo {
    public static void main(String[] args) {
        Customer c1 = new Customer("Amit", "amit@gmail.com");

        Product p1 = new Product("Laptop", 55000);
        Product p2 = new Product("Mobile", 25000);
        Product p3 = new Product("Mouse", 800);
        Product p4 = new Product("Keyboard", 1200);

        Order o1 = new Order("ORD101");
        o1.addProduct(p1);
        o1.addProduct(p3);

        Order o2 = new Order("ORD102");
        o2.addProduct(p2);
        o2.addProduct(p4);

        c1.placeOrder(o1);
        c1.placeOrder(o2);

        System.out.println();
        c1.showCustomerOrders();
    }
}
