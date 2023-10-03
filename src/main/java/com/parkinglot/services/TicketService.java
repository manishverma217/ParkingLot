package com.parkinglot.services;

import com.parkinglot.exceptions.GateNotFoundException;
import com.parkinglot.models.*;
import com.parkinglot.repositories.GateRepository;
import com.parkinglot.repositories.ParkingLotRepository;
import com.parkinglot.repositories.TicketRepository;
import com.parkinglot.repositories.VehicleRepository;
import com.parkinglot.strategies.SlotAssignmentStrategy;
import com.parkinglot.strategies.SlotAssignmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(
            GateRepository gateRepository,
            VehicleRepository vehicleRepository,
            ParkingLotRepository parkingLotRepository,
            TicketRepository ticketRepository
    ) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(VehicleType vehicleType,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              Long gateId)
            throws GateNotFoundException {
        // Create a Ticket object
        // Assign Spot
        // Assign Time
        // Save to DB
        // return the created object
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        // Get Gate model from Gate Id
        Optional<Gate> gateOp = gateRepository.findGateById(gateId);
        if(!gateOp.isPresent()){
            throw new GateNotFoundException();
        }
        Gate gate = gateOp.get();
        ticket.setGate(gate);
        ticket.setGeneratedBy(gate.getOperator());

        Vehicle savedVehicle;
        Optional<Vehicle> vehicleOp = vehicleRepository.getVehicleByNumber(vehicleNumber);
        if(!vehicleOp.isPresent()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);

            savedVehicle = vehicleRepository.saveVehicle(vehicle);
        } else {
            savedVehicle = vehicleOp.get();
        }

        ticket.setVehicle(savedVehicle);
        SlotAllotmentStrategyType slotAllotmentStrategyType = parkingLotRepository
                                                                    .getParkingLotForGate(gate)
                                                                    .getSlotAllotmentStrategyType();
        SlotAssignmentStrategy slotAssignmentStrategy = SlotAssignmentStrategyFactory
                                                            .getSlotForType(slotAllotmentStrategyType);
        ticket.setParkingSlot(slotAssignmentStrategy.getSlot(gate , vehicleType));
        ticket.setNumber("TICKET - " + ticket.getId()); //generate a UUID

        return ticketRepository.saveTicket(ticket);
    }
}
