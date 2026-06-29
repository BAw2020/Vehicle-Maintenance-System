import java.util.Scanner;

// Vehicle Class
class Vehicle {
    private String name;
    private double targetFuelEfficiency;
    private double nextServiceMileage;
    private double currentOdometer;

    public Vehicle(String name, double targetFuelEfficiency,
                   double nextServiceMileage, double currentOdometer) {
        this.name = name;
        this.targetFuelEfficiency = targetFuelEfficiency;
        this.nextServiceMileage = nextServiceMileage;
        this.currentOdometer = currentOdometer;
    }

    public void updateOdometer(double distance) {
        currentOdometer += distance;
    }

    public void checkServiceStatus() {
        if (currentOdometer >= nextServiceMileage) {
            System.out.println("SERVICE ALERT!");
            System.out.println("Current Odometer: " + currentOdometer + " km");
            System.out.println("Next Service Mileage: " + nextServiceMileage + " km");
        } else {
            System.out.println("Service Status: OK");
            System.out.println("Remaining until service: "
                    + (nextServiceMileage - currentOdometer) + " km");
        }
    }

    public double getTargetFuelEfficiency() {
        return targetFuelEfficiency;
    }

    public String getName() {
        return name;
    }

    public double getCurrentOdometer() {
        return currentOdometer;
    }
}

// Trip Class
class Trip {
    private String tripName;
    private double startingOdometer;
    private double endingOdometer;
    private double startingFuel;
    private double endingFuel;

    public Trip(String tripName,
                double startingOdometer,
                double endingOdometer,
                double startingFuel,
                double endingFuel) {

        this.tripName = tripName;
        this.startingOdometer = startingOdometer;
        this.endingOdometer = endingOdometer;
        this.startingFuel = startingFuel;
        this.endingFuel = endingFuel;
    }

    public double calculateDistance() {
        return endingOdometer - startingOdometer;
    }

    public double calculateFuelConsumed() {
        return startingFuel - endingFuel;
    }

    public double calculateFuelEfficiency() {
        double fuelConsumed = calculateFuelConsumed();

        if (fuelConsumed <= 0) {
            return 0;
        }

        return calculateDistance() / fuelConsumed;
    }

    public String getTripName() {
        return tripName;
    }
}

// Main Class
public class VehicleMaintenanceSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Vehicle Initialization
        System.out.println("=== Vehicle Initialization ===");

        System.out.print("Enter motorcycle name: ");
        String name = scanner.nextLine();

        System.out.print("Enter target fuel efficiency (km/L): ");
        double targetFuelEfficiency = scanner.nextDouble();

        System.out.print("Enter next service mileage (km): ");
        double nextServiceMileage = scanner.nextDouble();

        System.out.print("Enter current odometer reading (km): ");
        double currentOdometer = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(
                name,
                targetFuelEfficiency,
                nextServiceMileage,
                currentOdometer);

        boolean running = true;

        while (running) {

            scanner.nextLine(); // consume newline

            System.out.println("\n=== Log New Trip ===");

            System.out.print("Enter trip name: ");
            String tripName = scanner.nextLine();

            System.out.print("Enter starting odometer: ");
            double startOdometer = scanner.nextDouble();

            System.out.print("Enter ending odometer: ");
            double endOdometer = scanner.nextDouble();

            System.out.print("Enter starting fuel (L): ");
            double startFuel = scanner.nextDouble();

            System.out.print("Enter ending fuel (L): ");
            double endFuel = scanner.nextDouble();

            Trip trip = new Trip(
                    tripName,
                    startOdometer,
                    endOdometer,
                    startFuel,
                    endFuel);

            double distance = trip.calculateDistance();
            double fuelConsumed = trip.calculateFuelConsumed();
            double efficiency = trip.calculateFuelEfficiency();

            System.out.println("\n=== Trip Summary ===");
            System.out.println("Trip Name: " + trip.getTripName());
            System.out.println("Distance Travelled: " + distance + " km");
            System.out.println("Fuel Consumed: " + fuelConsumed + " L");
            System.out.printf("Fuel Efficiency: %.2f km/L%n", efficiency);

            if (efficiency >= vehicle.getTargetFuelEfficiency()) {
                System.out.println("Fuel efficiency is meeting the target.");
            } else {
                System.out.println("Fuel efficiency is below the target.");
            }

            // Update vehicle odometer
            vehicle.updateOdometer(distance);

            System.out.println("\n=== Service Check ===");
            vehicle.checkServiceStatus();

            System.out.print("\nDo you want to log another trip? (Y/N): ");
            char choice = scanner.next().charAt(0);

            if (choice == 'N' || choice == 'n') {
                running = false;
            }
        }

        System.out.println("\nThank you for using the Vehicle Maintenance & Fuel Tracking System.");

        scanner.close();
    }
}