import java.util.*;

class Vehicle {
    String vehicleId, brand, model, fuelType, currentStatus;
    int year;
    double mileage;
    static int totalVehicles;
    static double fleetValue, totalFuelConsumption;
    static String companyName;

    Vehicle(String vehicleId, String brand, String model, int year, double mileage, String fuelType, String status) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.currentStatus = status;
        totalVehicles++;
    }

    void updateMileage(double km) {
        mileage += km;
    }

    void assignDriver(Driver d) {
        d.assignedVehicle = this;
    }

    void scheduleMaintenance() {
        currentStatus = "Maintenance";
    }

    void checkServiceDue() {
        if (mileage > 5000) currentStatus = "Service Due";
    }

    double calculateRunningCost(double fuelPrice, double consumption) {
        totalFuelConsumption += consumption;
        return fuelPrice * consumption;
    }
}

class Car extends Vehicle {
    int seatingCapacity;
    Car(String id, String b, String m, int y, double mil, String f, String s, int seat) {
        super(id,b,m,y,mil,f,s);
        seatingCapacity = seat;
    }
}

class Bus extends Vehicle {
    int seatingCapacity;
    Bus(String id, String b, String m, int y, double mil, String f, String s, int seat) {
        super(id,b,m,y,mil,f,s);
        seatingCapacity = seat;
    }
}

class Truck extends Vehicle {
    double loadCapacity;
    Truck(String id, String b, String m, int y, double mil, String f, String s, double load) {
        super(id,b,m,y,mil,f,s);
        loadCapacity = load;
    }
}

class Driver {
    String driverId, driverName, licenseType;
    Vehicle assignedVehicle;
    int totalTrips;
    Driver(String id, String name, String lic) {
        driverId=id; driverName=name; licenseType=lic;
    }
    void completeTrip(double km) {
        if (assignedVehicle!=null) {
            assignedVehicle.updateMileage(km);
            totalTrips++;
        }
    }
}

public class FleetManagement {
    static double calculateTotalMaintenanceCost(Vehicle[] vehicles) {
        return vehicles.length*1000;
    }

    static double getFleetUtilization(Vehicle[] vehicles) {
        double used=0;
        for(Vehicle v:vehicles) if(!v.currentStatus.equals("Available")) used++;
        return (used/vehicles.length)*100;
    }

    static List<Vehicle> getVehiclesByType(Vehicle[] vehicles, String type) {
        List<Vehicle> list=new ArrayList<>();
        for(Vehicle v:vehicles) if(v.getClass().getSimpleName().equalsIgnoreCase(type)) list.add(v);
        return list;
    }

    public static void main(String[] args) {
        Vehicle.companyName="Transpo Pvt Ltd";
        Car c1=new Car("C1","Toyota","Corolla",2020,2000,"Petrol","Available",5);
        Bus b1=new Bus("B1","Volvo","9400",2019,7000,"Diesel","Available",40);
        Truck t1=new Truck("T1","Tata","LPT",2021,4000,"Diesel","Available",10);

        Driver d1=new Driver("D1","Raj","LMV");
        Driver d2=new Driver("D2","Anil","HMV");

        c1.assignDriver(d1);
        t1.assignDriver(d2);

        d1.completeTrip(300);
        d2.completeTrip(600);

        Vehicle[] fleet={c1,b1,t1};

        System.out.println("Company: "+Vehicle.companyName);
        for(Vehicle v:fleet)
            System.out.println(v.vehicleId+" "+v.brand+" "+v.model+" "+v.mileage+"km Status:"+v.currentStatus);

        System.out.println("Fleet Utilization: "+getFleetUtilization(fleet)+"%");
        System.out.println("Maintenance Cost: "+calculateTotalMaintenanceCost(fleet));
        System.out.println("Trucks: "+getVehiclesByType(fleet,"Truck").size());
    }
}
