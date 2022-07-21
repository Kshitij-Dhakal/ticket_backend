package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.HallMovie;
import com.kshitij.ticket.domain.Ticket;
import com.kshitij.ticket.domain.User;
import com.kshitij.ticket.error.FailedException;
import com.kshitij.ticket.error.NotFoundException;
import com.kshitij.ticket.error.ValidationException;
import com.kshitij.ticket.repo.HallMovieRepo;
import com.kshitij.ticket.repo.TicketRepo;
import com.kshitij.ticket.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TicketServiceImpl implements TicketService {
  private final UserRepo userRepo;
  private final HallMovieRepo hallMovieRepo;
  private final TicketRepo ticketRepo;

  @Override
  public Ticket reserveTicket(String email, Ticket ticket) {
    log.info("Reserving ticket for user : {}, Hall movie id : {}", email, ticket.getShow().getId());
    User user = userRepo.findByEmail(email);
    Optional<HallMovie> hallMovie = hallMovieRepo.findById(ticket.getShow().getId());
    if (hallMovie.isEmpty()) {
      throw new NotFoundException("This reservation is not possible");
    }
    long reserved = hallMovie.get().getReserved();
    long capacity = hallMovie.get().getHall().getCapacity();
    if (capacity - reserved < 1) {
      throw new FailedException("All reserved");
    }
    if (hallMovie.get().getDate().getTime() < System.currentTimeMillis()) {
      throw new ValidationException("Date already passed");
    }
    hallMovie.get().setReserved(hallMovie.get().getReserved() + 1);
    ticket.setShow(hallMovie.get());
    ticket.setUser(user);
    ticket.setDate(hallMovie.get().getDate());
    return ticketRepo.save(ticket);
  }

  @Override
  public List<Ticket> getTicketByEmail(String email) {
    log.info("Getting tickets for user : {}", email);
    User user = userRepo.findByEmail(email);
    return ticketRepo.findByUser(user);
  }
}