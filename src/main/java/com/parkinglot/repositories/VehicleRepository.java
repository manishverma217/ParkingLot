package com.parkinglot.repositories;

import com.parkinglot.models.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {
    private Map<Long , Vehicle> vehicles = new HashMap<>();
    private int vehicleId = 0;

    public Optional<Vehicle> getVehicleByNumber(String number){
        for(Vehicle vehicle : vehicles.values()){
            if(vehicle.getNumber().contains(number)){
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }
    public Vehicle saveVehicle(Vehicle vehicle){
        vehicleId++;
        vehicle.setId((long)vehicleId);
        vehicles.put(vehicle.getId() , vehicle);
        return vehicle;
    }
}
