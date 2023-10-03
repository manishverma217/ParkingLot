package com.parkinglot.controllers;

import com.parkinglot.dtos.IssueTicketRequestDto;
import com.parkinglot.dtos.IssueTicketResponseDto;
import com.parkinglot.dtos.ResponseStatus;
import com.parkinglot.models.Ticket;
import com.parkinglot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto issueTicketRequestDto){
        IssueTicketResponseDto issueTicketResponseDto = new IssueTicketResponseDto();
        Ticket ticket;
        try{
            ticket = ticketService.issueTicket(
                    issueTicketRequestDto.getVehicleType(),
                    issueTicketRequestDto.getVehicleNumber(),
                    issueTicketRequestDto.getVehicleOwnerName(),
                    issueTicketRequestDto.getGateId()
            );
        } catch(Exception e){
            issueTicketResponseDto.setResponseStatus(ResponseStatus.ERROR);
            issueTicketResponseDto.setErrorMessage(e.getMessage());
            return issueTicketResponseDto;
        }
        issueTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        issueTicketResponseDto.setTicket(ticket);
        return issueTicketResponseDto;
    }
}
