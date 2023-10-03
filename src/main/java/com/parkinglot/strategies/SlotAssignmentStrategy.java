package com.parkinglot.strategies;

import com.parkinglot.models.Gate;
import com.parkinglot.models.ParkingSlot;
import com.parkinglot.models.VehicleType;

public interface SlotAssignmentStrategy {
    public ParkingSlot getSlot(Gate gate , VehicleType vehicleType);
}
