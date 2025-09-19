package LLD.ParkingLot;

import LLD.ParkingLot.Vehicle.Bike;
import LLD.ParkingLot.Vehicle.Car;
import LLD.ParkingLot.Vehicle.Vehicle;

public class DriverMethod {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot lot = ParkingLot.getInstance(2, 2, 2, 1); // 2 floors, 2 car slots, 2 bike slots, 1 truck slot each

        Vehicle car1 = new Car("KA-01-1234");
        Vehicle bike1 = new Bike("KA-02-5678");

        Ticket t1 = lot.parkVehicle(car1);
        Ticket t2 = lot.parkVehicle(bike1);

        Thread.sleep(2000); // simulate some time

        lot.unparkVehicle(t1.getTicketId());
        lot.unparkVehicle(t2.getTicketId());
    }
}
