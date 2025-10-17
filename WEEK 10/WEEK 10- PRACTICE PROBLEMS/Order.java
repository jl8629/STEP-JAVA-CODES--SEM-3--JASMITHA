import java.util.*;

public class Order {
    private String orderId;
    private List<Product> products;

    public Order(String orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Added product '" + product.getName() + "' to Order " + orderId);
    }

    public void showOrderDetails() {
        System.out.println("Order " + orderId + " contains:");
        for (Product product : products) {
            product.showDetails();
        }
    }
}
