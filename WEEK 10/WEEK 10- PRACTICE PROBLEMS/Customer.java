import java.util.*;

public class Customer {
    private String name;
    private String email;
    private List<Order> orders;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
        //System.out.println(name + " placed Order " + order.orderId);
    }

    public void showCustomerOrders() {
        System.out.println("Orders placed by " + name + ":");
        for (Order order : orders) {
            order.showOrderDetails();
        }
    }
}
