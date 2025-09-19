package LLD.ParkingLot;

public class Payment {
    double calculateFee(Ticket ticket) {
        long minutes = ticket.getDurationMinutes();
        // For simplicity: ₹10 per hour (rounded up)
        return Math.ceil(minutes / 60.0) * 10;
    }
}
