package com.parkinglot.strategies;

import com.parkinglot.models.Gate;
import com.parkinglot.models.ParkingSlot;
import com.parkinglot.models.VehicleType;

public class RandomSlotAssignmentStrategy implements SlotAssignmentStrategy{
    @Override
    public ParkingSlot getSlot(Gate gate, VehicleType vehicleType) {
        return null;
    }
}
