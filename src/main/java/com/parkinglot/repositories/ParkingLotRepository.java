package com.parkinglot.repositories;

import com.parkinglot.models.Gate;
import com.parkinglot.models.ParkingLot;
import com.parkinglot.models.ParkingSlot;

import java.util.Map;
import java.util.TreeMap;

public class ParkingLotRepository {
    private Map<Long , ParkingLot> parkingLots = new TreeMap<>();

    public ParkingLot getParkingLotForGate(Gate gate){
        for(ParkingLot parkingLot : parkingLots.values()){
            if(parkingLot.getGates().contains(gate)){
                return parkingLot;
            }
        }
        return null;
    }
}
