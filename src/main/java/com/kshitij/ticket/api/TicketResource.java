package com.kshitij.ticket.api;

import com.kshitij.ticket.domain.Ticket;
import com.kshitij.ticket.dto.ReserveTicketRequest;
import com.kshitij.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TicketResource {
  private final TicketService ticketService;

  @PostMapping("/ticket")
  public ResponseEntity<Ticket> reserveTicket(
      Authentication authentication, @RequestBody ReserveTicketRequest reserveTicketRequest) {
    String email = (String) authentication.getPrincipal();
    return ResponseEntity.ok(ticketService.reserveTicket(email, reserveTicketRequest));
  }

  @GetMapping("/ticket")
  public ResponseEntity<List<Ticket>> getTicketByEmail(Authentication authentication) {
    String email = (String) authentication.getPrincipal();
    return ResponseEntity.ok(ticketService.getTicketByEmail(email));
  }

  @GetMapping("/dashboard/tickets")
  public ResponseEntity<List<Ticket>> getAllReservations() {
    return ResponseEntity.ok(ticketService.findAllReservations());
  }
}
