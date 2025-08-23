class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;

    Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }

    void startVehicle() {
        System.out.println(make + " " + model + " started.");
    }

    void stopVehicle() {
        System.out.println(make + " " + model + " stopped.");
    }

    void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled. Fuel Level: " + fuelLevel);
    }

    void displayVehicleInfo() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Fuel Level: " + fuelLevel);
        System.out.println("\n");
    }
}

class Car extends Vehicle {
    Car(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

class Truck extends Vehicle {
    Truck(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

class Motorcycle extends Vehicle {
    Motorcycle(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Vehicle v1 = new Car("Toyota", "Corolla", 2019, 50);
        Vehicle v2 = new Truck("Ford", "F-150", 2018, 70);
        Vehicle v3 = new Motorcycle("Honda", "CBR500R", 2021, 15);

        Vehicle[] vehicles = {v1, v2, v3};

        for (Vehicle v : vehicles) {
            v.startVehicle();
            v.displayVehicleInfo();
            v.refuel(10);
            v.stopVehicle();
        }
    }
}
