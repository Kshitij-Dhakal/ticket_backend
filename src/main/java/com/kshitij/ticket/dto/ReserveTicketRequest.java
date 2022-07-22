package com.kshitij.ticket.dto;

import com.kshitij.ticket.domain.Card;
import com.kshitij.ticket.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveTicketRequest {
  @NotNull(message = "Ticket detail is required")
  private Ticket ticket;

  @NotNull(message = "Card detail is required")
  private Card card;
}
