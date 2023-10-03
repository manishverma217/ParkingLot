package com.parkinglot;

import com.parkinglot.controllers.TicketController;
import com.parkinglot.models.Vehicle;
import com.parkinglot.repositories.GateRepository;
import com.parkinglot.repositories.ParkingLotRepository;
import com.parkinglot.repositories.TicketRepository;
import com.parkinglot.repositories.VehicleRepository;
import com.parkinglot.services.TicketService;

public class ParkingLotApplication {
    public static void main(String[] args) {
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );

        TicketController ticketController = new TicketController(ticketService);

        
    }
}

//Requirements:
// 1. Operator should be able to generate the ticket
// 2. Admin should be able to create a new Parking Lot

//MVC -> Model , View , Controller
