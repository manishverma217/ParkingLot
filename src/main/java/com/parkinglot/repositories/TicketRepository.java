package com.parkinglot.repositories;

import com.parkinglot.models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Long , Ticket> tickets = new HashMap<>();
    private int previousId = 0;
    public Ticket saveTicket(Ticket ticket){
        previousId++;
        ticket.setId((long)previousId);
        tickets.put((long)previousId , ticket);
        return ticket;
    }
}
