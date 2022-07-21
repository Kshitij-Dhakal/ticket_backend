package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Ticket;

import java.util.List;

public interface TicketService {
  Ticket reserveTicket(String email, Ticket ticket);

  List<Ticket> getTicketByEmail(String email);
}
