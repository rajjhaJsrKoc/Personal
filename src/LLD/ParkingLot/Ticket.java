package LLD.ParkingLot;

import java.util.Date;

class Ticket {
    String ticketId;
    ParkingSlot slot;
    Date entryTime;

    Ticket(String ticketId, ParkingSlot slot) {
        this.ticketId = ticketId;
        this.slot = slot;
        this.entryTime = new Date();
    }

    long getDurationMinutes() {
        long diff = new Date().getTime() - entryTime.getTime();
        return diff / (1000 * 60);
    }

    public String getTicketId() {
        return ticketId;
    }

    public ParkingSlot getSlot() {
        return slot;
    }
}
