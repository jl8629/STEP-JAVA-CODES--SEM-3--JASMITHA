class FoodDelivery {
    public void calculateDeliveryCharge(double distance) {
        double cost = distance * 10;
        System.out.println("Basic Delivery: Distance = " + distance + " km");
        System.out.println("Delivery Cost = Rs." + cost);
    }

    public void calculateDeliveryCharge(double distance, double priorityFee) {
        double cost = distance * 10 + priorityFee;
        System.out.println("Premium Delivery: Distance = " + distance + " km + Priority Fee Rs." + priorityFee);
        System.out.println("Delivery Cost = Rs." + cost);
    }

    public void calculateDeliveryCharge(double distance, int numberOfOrders) {
        double baseCost = distance * 10;
        double discount = numberOfOrders * 5;
        double finalCost = baseCost - discount;
        if (finalCost < 0) finalCost = 0;
        System.out.println("Group Delivery: Distance = " + distance + " km with " + numberOfOrders + " orders");
        System.out.println("Discount = Rs." + discount);
        System.out.println("Delivery Cost = Rs." + finalCost);
    }

    public void calculateDeliveryCharge(double distance, double discountPercentage, double freeDeliveryThreshold, double orderAmount) {
        double cost = distance * 10;
        if (orderAmount >= freeDeliveryThreshold) {
            cost = 0;
            System.out.println("Festival Special: Order amount Rs." + orderAmount + " qualifies for FREE delivery!");
        } else {
            double discount = (cost * discountPercentage) / 100;
            cost -= discount;
            System.out.println("Festival Special: Distance = " + distance + " km, Discount = " + discountPercentage + "%");
            System.out.println("Delivery Cost before discount = Rs." + (distance * 10));
            System.out.println("Discount Applied = Rs." + discount);
        }
        System.out.println("Final Delivery Cost = Rs." + cost);
    }
}

public class FoodDeliveryApp {
    public static void main(String[] args) {
        FoodDelivery fd = new FoodDelivery();
        fd.calculateDeliveryCharge(5);
        fd.calculateDeliveryCharge(5, 50);
        fd.calculateDeliveryCharge(5, 3);
        fd.calculateDeliveryCharge(5, 20, 500, 450);
        fd.calculateDeliveryCharge(5, 20, 500, 600);
    }
}
