import java.time.LocalDateTime;
import java.util.*;

final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String productId, String name, String category, String manufacturer, double basePrice, double weight, String[] features, Map<String, String> specifications) {
        if (productId == null || productId.isEmpty() || name == null || category == null || manufacturer == null || basePrice < 0 || weight < 0)
            throw new IllegalArgumentException("Invalid product data");
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = features != null ? features.clone() : new String[0];
        this.specifications = specifications != null ? new HashMap<>(specifications) : new HashMap<>();
    }

    public static Product createElectronics(String id, String name, double price, double weight, String[] features, Map<String, String> specs) {
        return new Product(id, name, "Electronics", "Generic Electronics", price, weight, features, specs);
    }

    public static Product createClothing(String id, String name, double price, double weight, String[] features, Map<String, String> specs) {
        return new Product(id, name, "Clothing", "Generic Clothing", price, weight, features, specs);
    }

    public static Product createBooks(String id, String name, double price, double weight, String[] features, Map<String, String> specs) {
        return new Product(id, name, "Books", "Generic Publisher", price, weight, features, specs);
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return features.clone(); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }

    public final double calculateTax(String region) {
        if ("US".equalsIgnoreCase(region)) return basePrice * 0.07;
        if ("EU".equalsIgnoreCase(region)) return basePrice * 0.20;
        return basePrice * 0.10;
    }

    @Override
    public String toString() {
        return "Product{" + "id='" + productId + "', name='" + name + "', category='" + category + "', price=" + basePrice + "}";
    }
    @Override
    public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof Product)) return false; Product product = (Product) o; return Objects.equals(productId, product.productId); }
    @Override
    public int hashCode() { return Objects.hash(productId); }
}

class Customer {
    private final String customerId;
    private final String email;
    private final String accountCreationDate;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;

    public Customer(String customerId, String email, String accountCreationDate) {
        if (customerId == null || email == null || accountCreationDate == null) throw new IllegalArgumentException("Invalid customer");
        this.customerId = customerId;
        this.email = email;
        this.accountCreationDate = accountCreationDate;
    }

    String getCreditRating() { return "Good"; }
    public String getPublicProfile() { return "Customer{" + "id='" + customerId + "', name='" + name + "'}"; }

    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getAccountCreationDate() { return accountCreationDate; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }

    @Override
    public String toString() { return "Customer{" + "id='" + customerId + "', email='" + email + "'}"; }
}

class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<Object> items;
    private double totalAmount;
    private int itemCount;

    public ShoppingCart(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product) || quantity <= 0) return false;
        Product p = (Product) product;
        items.add(p);
        itemCount += quantity;
        totalAmount += p.getBasePrice() * quantity;
        totalAmount -= calculateDiscount();
        return true;
    }

    private double calculateDiscount() {
        if (itemCount > 5) return totalAmount * 0.05;
        return 0.0;
    }

    String getCartSummary() { return "Cart{" + "cartId='" + cartId + "', items=" + itemCount + ", total=" + totalAmount + "}"; }
    public String getCartId() { return cartId; }
    public String getCustomerId() { return customerId; }
    public double getTotalAmount() { return totalAmount; }
    public int getItemCount() { return itemCount; }
}

class Order {
    private final String orderId;
    private final LocalDateTime orderTime;

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderTime = LocalDateTime.now();
    }
    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
}

class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    public PaymentProcessor(String processorId, String securityKey) {
        this.processorId = processorId;
        this.securityKey = securityKey;
    }
    public boolean processPayment(double amount) { return amount > 0; }
}

class ShippingCalculator {
    private final Map<String, Double> shippingRates;

    public ShippingCalculator(Map<String, Double> shippingRates) {
        this.shippingRates = shippingRates != null ? new HashMap<>(shippingRates) : new HashMap<>();
    }
    public double calculateShipping(String region, double weight) {
        return shippingRates.getOrDefault(region, 10.0) * weight;
    }
}

final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new HashMap<>();

    public static boolean processOrder(Object order, Object customer) {
        if (!(order instanceof Order) || !(customer instanceof Customer)) return false;
        return true;
    }

    public static void addProductToCatalog(String id, Product product) { productCatalog.put(id, product); }
    public static Object getProductFromCatalog(String id) { return productCatalog.get(id); }
}

public class MainECommerce {
    public static void main(String[] args) {
        Product laptop = Product.createElectronics("P1001", "Laptop", 1000.0, 2.5, new String[]{"i7 CPU", "16GB RAM"}, Map.of("Color", "Black"));
        Customer customer = new Customer("C001", "alice@example.com", "2022-01-01");
        customer.setName("Alice");
        ShoppingCart cart = new ShoppingCart("CART001", customer.getCustomerId());
        cart.addItem(laptop, 2);
        Order order = new Order("O001");
        PaymentProcessor processor = new PaymentProcessor("PAY001", "SECUREKEY");
        ShippingCalculator shipCalc = new ShippingCalculator(Map.of("US", 5.0, "EU", 8.0));
        System.out.println(cart.getCartSummary());
        System.out.println(processor.processPayment(cart.getTotalAmount()));
        System.out.println(shipCalc.calculateShipping("US", laptop.getWeight()));
        System.out.println(ECommerceSystem.processOrder(order, customer));
    }
}
