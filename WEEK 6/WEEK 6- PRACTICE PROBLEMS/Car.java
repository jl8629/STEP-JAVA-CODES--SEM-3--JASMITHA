public class Car extends Vehicle {

    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    public Car() {
        super(); 
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }

    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType); // Call Vehicle constructor
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    @Override
    public void start() {
        super.start(); 
        System.out.println("Car-specific startup sequence complete");
    }

    @Override
    public void displaySpecs() {
        super.displaySpecs();
        System.out.println("Car Specs:");
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Transmission: " + transmissionType);
    }

    public void openTrunk() {
        System.out.println("Trunk opened");
    }

    public void playRadio() {
        System.out.println("Radio playing music");
    }

    public static void main(String[] args) {
        System.out.println("\nCreating Car using default constructor: ");
        Car defaultCar = new Car();

        System.out.println("\nCreating Car using parameterized constructor: ");
        Car paramCar = new Car("Toyota", "Corolla", 2022, "Hybrid", 4, "Petrol", "Automatic");

        System.out.println("\nTesting inherited fields and methods: ");
        System.out.println("Brand: " + paramCar.brand); 
        System.out.println("Model: " + paramCar.model);
        System.out.println("Year: " + paramCar.year);
        System.out.println("Engine: " + paramCar.engineType);
        System.out.println("Registration: " + paramCar.getRegistrationNumber());

        paramCar.start();
        paramCar.displaySpecs();
        paramCar.stop();
        paramCar.openTrunk();
        paramCar.playRadio();

        System.out.println("\nPolymorphic behavior: ");
        Vehicle v = new Car("Honda", "Civic", 2021, "Petrol", 4, "Petrol", "Manual");
        v.start();   
        v.displaySpecs(); 
    }
}
