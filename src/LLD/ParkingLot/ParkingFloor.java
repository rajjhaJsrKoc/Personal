package LLD.ParkingLot;

import LLD.ParkingLot.Vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    int floorId;
    List<ParkingSlot> slots;

    ParkingFloor(int floorId, int carSlots, int bikeSlots, int truckSlots) {
        this.floorId = floorId;
        this.slots = new ArrayList<>();
        int idCounter = 1;

        for (int i = 0; i < carSlots; i++) slots.add(new ParkingSlot(idCounter++, VehicleType.CAR));
        for (int i = 0; i < bikeSlots; i++) slots.add(new ParkingSlot(idCounter++, VehicleType.BIKE));
        for (int i = 0; i < truckSlots; i++) slots.add(new ParkingSlot(idCounter++, VehicleType.TRUCK));
    }

    ParkingSlot  getFreeSlot(VehicleType type) {
        for (ParkingSlot slot : slots) {
            if (slot.type == type && slot.isAvailable()) {
                return slot;
            }
        }
        return null;
    }
}
