class Phone {
    protected String brand;
    protected String model;

    public Phone() {
        System.out.println("Phone default constructor called");
    }

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone parameterized constructor called");
    }
}

class SmartPhone extends Phone {
    private String operatingSystem;

    public SmartPhone() {
        super();
        System.out.println("SmartPhone default constructor called");
    }

    public SmartPhone(String brand, String model, String operatingSystem) {
        super(brand, model);
        this.operatingSystem = operatingSystem;
        System.out.println("SmartPhone parameterized constructor called");
    }

    public void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Operating System: " + operatingSystem);
    }
}

public class Phones {
    public static void main(String[] args) {
        System.out.println("Creating Phone Object: ");
        Phone p1 = new Phone("Nokia", "1100");
        System.out.println(p1);

        System.out.println("\nCreating SmartPhone with default constructor: ");
        SmartPhone sp1 = new SmartPhone();
        System.out.println(sp1);

        System.out.println("\nCreating SmartPhone with parameterized constructor: ");
        SmartPhone sp2 = new SmartPhone("Apple", "iPhone 15", "iOS");
        sp2.displayDetails();
    }
}
