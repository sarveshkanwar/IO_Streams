package tdd.CabInvoice.cabInvoicegenrtr;

public class InvoiceService {
    private final RideRepository rideRepository = new RideRepository();

    public double calculateFare(Ride ride) {
        double costPerKm, costPerMin, minFare;
        if (ride.rideType == RideType.PREMIUM) {
            costPerKm = 15;
            costPerMin = 2;
            minFare = 20;
        } else {
            costPerKm = 10;
            costPerMin = 1;
            minFare = 5;
        }

        double fare = ride.distance * costPerKm + ride.time * costPerMin;
        return Math.max(fare, minFare);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = rideRepository.getRides(userId);
        return calculateFare(rides);
    }
}
