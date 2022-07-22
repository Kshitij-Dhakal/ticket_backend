package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Ticket;
import com.kshitij.ticket.dto.ReserveTicketRequest;

import java.util.List;

public interface TicketService {
  Ticket reserveTicket(String email, ReserveTicketRequest reserveTicketRequest);

  List<Ticket> getTicketByEmail(String email);
}
