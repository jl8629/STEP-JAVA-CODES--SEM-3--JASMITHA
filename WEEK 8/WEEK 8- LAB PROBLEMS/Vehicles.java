abstract class Vehicle {
    protected int speed;
    protected String fuelType;
    abstract void startEngine();
}

interface Maintainable {
    void serviceInfo();
}

class Car extends Vehicle implements Maintainable {
    private String model;
    Car(String model, int speed, String fuelType) {
        this.model = model;
        this.speed = speed;
        this.fuelType = fuelType;
    }
    void startEngine() {
        System.out.println(model + " engine started. Fuel Type: " + fuelType);
    }
    public void serviceInfo() {
        System.out.println(model + " requires servicing every 6 months or 10000 km");
    }
    void showDetails() {
        System.out.println("Model: " + model + ", Speed: " + speed + " km/h, Fuel: " + fuelType);
    }
}

public class Vehicles {
    public static void main(String[] args) {
        Car c = new Car("Honda City", 180, "Petrol");
        c.startEngine();
        c.showDetails();
        c.serviceInfo();
    }
}
