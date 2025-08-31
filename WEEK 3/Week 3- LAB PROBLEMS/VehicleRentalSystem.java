import java.util.Scanner;

class Vehicle {
    private String vehicleId, brand, model;
    private double rentPerDay;
    private boolean isAvailable;
    private static int totalVehicles=0, idCount=1, rentalDays=0;
    private static double totalRevenue=0;
    private static String companyName="Default Rentals";
    private int rentedDays=0;

    public Vehicle(String brand,String model,double rentPerDay){
        this.vehicleId="V"+idCount++;
        this.brand=brand;
        this.model=model;
        this.rentPerDay=rentPerDay;
        this.isAvailable=true;
        totalVehicles++;
    }

    public double calculateRent(int days){
        double amount=days*rentPerDay;
        totalRevenue+=amount;
        rentalDays+=days;
        rentedDays+=days;
        return amount;
    }

    public void rentVehicle(int days){
        if(isAvailable){
            double cost=calculateRent(days);
            isAvailable=false;
            System.out.println(vehicleId+" rented for "+days+" days. Cost: "+cost);
        } else {
            System.out.println(vehicleId+" is not available");
        }
    }

    public void returnVehicle(){
        if(!isAvailable){
            isAvailable=true;
            System.out.println(vehicleId+" returned");
        }
    }

    public void displayVehicleInfo(){
        System.out.println("ID: "+vehicleId+" Brand: "+brand+" Model: "+model+" Rent/Day: "+rentPerDay+" Available: "+isAvailable+" RentedDays: "+rentedDays);
    }

    public static void setCompanyName(String name){companyName=name;}
    public static double getTotalRevenue(){return totalRevenue;}
    public static double getAverageRentPerDay(){return rentalDays==0?0:totalRevenue/rentalDays;}
    public static void displayCompanyStats(){
        System.out.println("Company: "+companyName);
        System.out.println("Total Vehicles: "+totalVehicles);
        System.out.println("Total Revenue: "+totalRevenue);
        System.out.println("Average Rent per Day: "+getAverageRentPerDay());
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Vehicle.setCompanyName("City Rentals");

        System.out.print("Enter number of vehicles: ");
        int n=sc.nextInt();
        Vehicle[] vehicles=new Vehicle[n];

        for(int i=0;i<n;i++){
            System.out.print("Enter brand of vehicle "+(i+1)+": ");
            String brand=sc.next();
            System.out.print("Enter model of vehicle "+(i+1)+": ");
            String model=sc.next();
            System.out.print("Enter rent per day of vehicle "+(i+1)+": ");
            double rent=sc.nextDouble();
            vehicles[i]=new Vehicle(brand,model,rent);
        }

        vehicles[0].rentVehicle(3);
        vehicles[1].rentVehicle(2);
        vehicles[0].returnVehicle();
        vehicles[0].rentVehicle(1);

        System.out.println("\nVehicle Info:");
        for(Vehicle v:vehicles) v.displayVehicleInfo();

        System.out.println("\nCompany Stats:");
        Vehicle.displayCompanyStats();

        sc.close();
    }
}
