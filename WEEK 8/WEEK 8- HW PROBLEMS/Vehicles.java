abstract class Vehicle {
    abstract void start();
    void stop() {
        System.out.println("Vehicle stopped");
    }
}

interface Fuel {
    void refuel();
}

class Car extends Vehicle implements Fuel {
    private String model;
    Car(String model) {
        this.model = model;
    }
    void start() {
        System.out.println(model + " started");
    }
    public void refuel() {
        System.out.println(model + " is refueling");
    }
}

public class Vehicles {
    public static void main(String[] args) {
        Car c = new Car("Toyota");
        c.start();
        c.refuel();
        c.stop();
    }
}
