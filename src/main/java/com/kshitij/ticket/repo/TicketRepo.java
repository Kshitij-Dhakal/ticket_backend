package com.kshitij.ticket.repo;

import com.kshitij.ticket.domain.Ticket;
import com.kshitij.ticket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
  List<Ticket> findByUser(User user);
}
