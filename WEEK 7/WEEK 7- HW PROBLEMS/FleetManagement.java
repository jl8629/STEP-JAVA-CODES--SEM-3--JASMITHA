class Vehicle {
    void dispatch() {
        System.out.println("Dispatching a generic vehicle");
        System.out.println();
    }
}

class Bus extends Vehicle {
    private int passengerCapacity;
    private String route;

    Bus(String route, int passengerCapacity) {
        this.route = route;
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    void dispatch() {
        System.out.println("Bus Dispatch:");
        System.out.println("Route: " + route);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Following fixed route schedule");
        System.out.println();
    }
}

class Taxi extends Vehicle {
    private double distance;

    Taxi(double distance) {
        this.distance = distance;
    }

    @Override
    void dispatch() {
        double fare = distance * 2.5;
        System.out.println("Taxi Dispatch:");
        System.out.println("Distance: " + distance + " km");
        System.out.println("Fare: $" + fare);
        System.out.println("Door-to-door service activated");
        System.out.println();
    }
}

class Train extends Vehicle {
    private int carCount;
    private String schedule;

    Train(String schedule, int carCount) {
        this.schedule = schedule;
        this.carCount = carCount;
    }

    @Override
    void dispatch() {
        System.out.println("Train Dispatch:");
        System.out.println("Schedule: " + schedule);
        System.out.println("Number of Cars: " + carCount);
        System.out.println("Operating on schedule with multiple car capacity");
        System.out.println();
    }
}

class Bike extends Vehicle {
    private double tripDistance;

    Bike(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    void dispatch() {
        System.out.println("Bike Dispatch:");
        System.out.println("Trip Distance: " + tripDistance + " km");
        System.out.println("Eco-friendly short-distance trip");
        System.out.println();
    }
}

public class FleetManagement {
    public static void main(String[] args) {
        Vehicle[] fleet = {
            new Bus("Downtown to Airport", 50),
            new Taxi(12.5),
            new Train("08:00 AM", 10),
            new Bike(3)
        };

        for(Vehicle v : fleet) {
            v.dispatch();
        }
    }
}
