package LLD.ParkingLot;

import LLD.ParkingLot.Vehicle.Vehicle;

import java.util.*;

public class ParkingLot {
    private static ParkingLot instance;
    List<ParkingFloor> floors = new ArrayList<>();
    Map<String, Ticket> activeTickets;
    Payment payment;

    private ParkingLot(int floorsCount, int carSlots, int bikeSlots, int truckSlots) {
        for (int i = 1; i <= floorsCount; i++) {
            floors.add(new ParkingFloor(i, carSlots, bikeSlots, truckSlots));
        }
        this.activeTickets = new HashMap<>();
        this.payment = new Payment();
    }

    // Singleton instance
    public static synchronized ParkingLot getInstance(int floorsCount, int carSlots, int bikeSlots, int truckSlots) {
        if (instance == null) {
            instance = new ParkingLot(floorsCount, carSlots, bikeSlots, truckSlots);
        }
        return instance;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            ParkingSlot slot = floor.getFreeSlot(vehicle.getType());
            if (slot != null) {
                slot.assignVehicle(vehicle);
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = new Ticket(ticketId, slot);
                activeTickets.put(ticketId, ticket);
                System.out.println("Vehicle " + vehicle.getNumber() + " parked at Slot " + slot.slotId + " on Floor " + floor.floorId);
                return ticket;
            }
        }
        throw new RuntimeException("No available slots for " + vehicle.getType());
    }

    public double unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) throw new RuntimeException("Invalid ticket");

        ParkingSlot slot = ticket.getSlot();
        slot.removeVehicle();
        double fee = payment.calculateFee(ticket);
        activeTickets.remove(ticketId);
        System.out.println("Vehicle removed from Slot " + slot.slotId + ". Fee: ₹" + fee);
        return fee;
    }
}