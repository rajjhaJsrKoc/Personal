package LLD.ParkingLot;

import LLD.ParkingLot.Vehicle.Vehicle;
import LLD.ParkingLot.Vehicle.VehicleType;

public class ParkingSlot {
    int slotId;
    VehicleType type;
    boolean isFree;
    Vehicle vehicle;

    public ParkingSlot(int slotId, VehicleType type) {
        this.slotId = slotId;
        this.type = type;
        this.isFree = true;
    }
    boolean isAvailable() {
        return isFree;
    }
    void assignVehicle(Vehicle v) {
        if (isFree && v.getType() == type) {
            this.vehicle = v;
            this.isFree = false;
        } else {
            throw new RuntimeException("Slot not available or type mismatch");
        }
    }
    void removeVehicle() {
        this.vehicle = null;
        this.isFree = true;
    }
}
