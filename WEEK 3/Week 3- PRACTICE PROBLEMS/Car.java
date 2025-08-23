import java.util.*;

public class Car {
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;

    Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false;
    }

    void startEngine() {
        isRunning = true;
        System.out.println(brand + " " + model + " engine started.");
    }

    void stopEngine() {
        isRunning = false;
        System.out.println(brand + " " + model + " engine stopped.");
    }

    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
        System.out.println("Running: " + isRunning);
    }

    int getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - year;
    }

    public static void main(String[] args) {
        Car c1 = new Car("Toyota", "Corolla", 2015, "White");
        Car c2 = new Car("Honda", "Civic", 2018, "Black");
        Car c3 = new Car("Tesla", "Model 3", 2022, "Red");

        c1.startEngine();
        c1.displayInfo();
        System.out.println("Age: " + c1.getAge());
        c1.stopEngine();

        c2.startEngine();
        c2.displayInfo();
        System.out.println("Age: " + c2.getAge());
        c2.stopEngine();

        c3.startEngine();
        c3.displayInfo();
        System.out.println("Age: " + c3.getAge());
        c3.stopEngine();
    }
}
