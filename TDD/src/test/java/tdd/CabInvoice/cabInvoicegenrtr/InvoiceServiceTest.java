package tdd.CabInvoice.cabInvoicegenrtr;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceServiceTest {

    @Test
    public void calculateFareTest() {
        InvoiceService invoiceService = new InvoiceService();
        double fare = invoiceService.calculateFare(new Ride(RideType.NORMAL, 2.0, 5));
        assertEquals(25.0, fare, 0.01);
    }

    @Test
    public void multipleRideTest() {
        InvoiceService invoiceService = new InvoiceService();
        Ride[] rides = {
                new Ride(RideType.NORMAL, 2.0, 5),
                new Ride(RideType.NORMAL, 0.1, 1)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expected = new InvoiceSummary(2, 30.0);
        assertEquals(expected, summary);
    }

    @Test
    public void EnhancedInvoiceTest() {
        InvoiceService invoiceService = new InvoiceService();
        String userId = "user1";
        Ride[] rides = {
                new Ride(RideType.NORMAL, 2.0, 5),
                new Ride(RideType.NORMAL, 0.1, 1)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expected = new InvoiceSummary(2, 30.0);
        assertEquals(expected, summary);
    }

    @Test
    public void premiumRideTest() {
        InvoiceService invoiceService = new InvoiceService();
        double fare = invoiceService.calculateFare(new Ride(RideType.PREMIUM, 2.0, 5));
        assertEquals(40.0, fare, 0.01);
    }
}

