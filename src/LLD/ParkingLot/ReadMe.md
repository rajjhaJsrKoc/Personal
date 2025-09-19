
# 🚙 Parking Lot – Class Diagram

## 📌 Overview
This diagram represents the **Parking Lot system** design covering:
- ParkingLot (Singleton, entry/exit management)
- ParkingFloor, ParkingSlot
- Vehicle hierarchy (Car, Bike, Truck)
- Ticket & Payment

---

## 🔹 Class Diagram

```mermaid
classDiagram
    class ParkingLot {
        -List<ParkingFloor> floors
        -Map<String,Ticket> activeTickets
        -Payment payment
        +parkVehicle(vehicle: Vehicle): Ticket
        +unparkVehicle(ticketId: String): double
        +getInstance(floors,carSlots,bikeSlots,truckSlots)
    }

    class ParkingFloor {
        -int floorId
        -List<ParkingSlot> slots
        +getFreeSlot(type: VehicleType): ParkingSlot
    }

    class ParkingSlot {
        -int slotId
        -VehicleType type
        -boolean isFree
        -Vehicle vehicle
        +assignVehicle(vehicle: Vehicle)
        +removeVehicle()
        +isAvailable(): boolean
    }

    class Vehicle {
        <<abstract>>
        -String number
        -VehicleType type
        +getNumber()
        +getType()
    }

    class Car
    class Bike
    class Truck

    class Ticket {
        -String ticketId
        -ParkingSlot slot
        -Date entryTime
        +getDurationMinutes(): long
        +getTicketId(): String
        +getSlot(): ParkingSlot
    }

    class Payment {
        +calculateFee(ticket: Ticket): double
    }

    ParkingLot "1" --> "*" ParkingFloor
    ParkingFloor "1" --> "*" ParkingSlot
    ParkingSlot "1" --> "0..1" Vehicle
    Vehicle <|-- Car
    Vehicle <|-- Bike
    Vehicle <|-- Truck
    Ticket "1" --> "1" ParkingSlot
    ParkingLot --> Payment
````

---

## 🔹 Design Highlights

* **Singleton Pattern** → `ParkingLot` ensures only one instance exists.
* **Factory/Inheritance** → `Vehicle` is abstract; concrete classes are `Car`, `Bike`, `Truck`.
* **Strategy Ready** → `Payment` can be extended to support different fee rules.
* **Extensible** → Easy to add `EVSlot`, `VIPSlot`, or new vehicle types.


