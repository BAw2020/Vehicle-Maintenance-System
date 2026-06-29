import java.util.Scanner;

class Vehicle{
    private String name;
    private double eMilegae;
    private double sMileage;
    private double cOdometer;

    public Vehicle(String name, double eMilegae, double sMileage, double cOdometer){
        this.name = name;
        this.eMilegae = eMilegae;
        this.sMileage = sMileage;
        this.cOdometer = cOdometer;
    }

    public void checkServiceStatus(){
        if(cOdometer > sMileage || cOdometer == sMileage){
            System.out.println("It is time to service your vehicle!");
        }else{
            System.out.println("Next service at:"+sMileage+"km");
        }
    }

    public double getCurrentOdometer(){
        return cOdometer;
    }

    public void updateOdometer(double Distance){

        cOdometer = cOdometer + Distance;
    }
}

class Trip{
    private String tName;
    private double sReading;
    private double eReading;
    private double sFuel;
    private double eFuel;

    public Trip(String tName,double sReading,double eReading,double sFuel,double eFuel){
        this.tName = tName;
        this.sReading = sReading;
        this.eReading = eReading;
        this.sFuel = sFuel;
        this.eFuel = eFuel;
    }
    public double calculateDistance(){
        double distance = eReading-sReading;
        return distance;
    }
    public double calculateFuelConsumed(){
        double uFuel = sFuel-eFuel;
        return uFuel;
    }
    public double calculateFuelEfficiency(){
        double distance = calculateDistance();
        double uFuel = calculateFuelConsumed();
        double fConsumed = distance/uFuel;
        return fConsumed;
    }
}


    public class VehicleSystem{
        public static void main(String[] args){
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter your MotorCycle Name:");
            String name = scan.nextLine();

            System.out.println("Enter vehicle target km/l");
            double eMileage = scan.nextDouble();

            System.out.println("Enter the next service mileage (km):");
            double sMileage = scan.nextDouble();

            System.out.println("Enter the current odometer reading (km):");
            double cOdometer = scan.nextDouble();

            Vehicle myScooter = new Vehicle(name,eMileage,sMileage,cOdometer);

            int tripChoice = 1;

            while(tripChoice == 1){
                System.out.println("Enter your Trip Name:");
                String tName = scan.next();

                System.out.println("Enter the start odometer reading (km):");
                double sReading = scan.nextDouble();

                System.out.println("Enter the end odometer reading (km):");
                double eReading = scan.nextDouble();

                System.out.println("Enter the starting fuel amount(l)");
                double sFuel = scan.nextDouble();

                System.out.println("Enter the ending fuel amount(l)");
                double eFuel = scan.nextDouble();

                Trip currentTrip = new Trip(tName,sReading,eReading,sFuel,eFuel);

                double distance = currentTrip.calculateDistance();
                double efficiency = currentTrip.calculateFuelEfficiency();

                System.out.println("Fuel efficiency for " + tName + ":" + efficiency + "km/l" );

                myScooter.updateOdometer(distance);
                myScooter.checkServiceStatus();

                System.out.println("Do you want to log another trip (1= Yes ,0 = No)");
                tripChoice = scan.nextInt();


            }
            System.out.println("System shutting down.");
        }
    }


