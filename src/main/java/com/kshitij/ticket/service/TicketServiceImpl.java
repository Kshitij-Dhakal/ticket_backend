package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.ReservedSeats;
import com.kshitij.ticket.domain.Show;
import com.kshitij.ticket.domain.Ticket;
import com.kshitij.ticket.domain.User;
import com.kshitij.ticket.dto.ReserveTicketRequest;
import com.kshitij.ticket.error.FailedException;
import com.kshitij.ticket.error.NotFoundException;
import com.kshitij.ticket.error.ValidationException;
import com.kshitij.ticket.repo.HallMovieRepo;
import com.kshitij.ticket.repo.ReservedSeatsRepo;
import com.kshitij.ticket.repo.TicketRepo;
import com.kshitij.ticket.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TicketServiceImpl implements TicketService {
  private final UserRepo userRepo;
  private final HallMovieRepo hallMovieRepo;
  private final TicketRepo ticketRepo;
  private final ReservedSeatsRepo reservedSeatsRepo;

  @Override
  public Ticket reserveTicket(
      @NotNull String email, @Valid ReserveTicketRequest reserveTicketRequest) {
    log.info(
        "Reserving ticket for user : {}, Hall movie id : {}",
        email,
        reserveTicketRequest.getTicket().getShow().getId());
    User user = userRepo.findByEmail(email);
    Optional<Show> optionalShow =
        hallMovieRepo.findById(reserveTicketRequest.getTicket().getShow().getId());
    if (optionalShow.isEmpty()) {
      throw new NotFoundException("This reservation is not possible");
    }
    Show show = optionalShow.get();
    for (ReservedSeats i : reserveTicketRequest.getReservedSeats()) {
      if (i.getSeat() > show.getHall().getCapacity() - 1 || i.getSeat() < 0) {
        throw new ValidationException(
            "Invalid seat. It must be between 0 and " + (show.getHall().getCapacity() - 1));
      }
      if (show.getReservedSeats().contains(i)) {
        throw new FailedException("Ticket already reserved.");
      }
    }
    if (show.getDate().getTime() < System.currentTimeMillis()) {
      throw new ValidationException("Date already passed");
    }
    Set<ReservedSeats> reserved = new HashSet<>(show.getReservedSeats());
    reserved.addAll(reserveTicketRequest.getReservedSeats());
    List<ReservedSeats> reservedSeats = reservedSeatsRepo.saveAll(reserved);
    show.setReservedSeats(reservedSeats);
    reserveTicketRequest.getTicket().setShow(show);
    reserveTicketRequest.getTicket().setUser(user);
    reserveTicketRequest.getTicket().setDate(show.getDate());
    return ticketRepo.save(reserveTicketRequest.getTicket());
  }

  @Override
  public List<Ticket> getTicketByEmail(String email) {
    log.info("Getting tickets for user : {}", email);
    User user = userRepo.findByEmail(email);
    return ticketRepo.findByUser(user);
  }

  @Override
  public List<Ticket> findAllReservations() {
    log.info("Getting all reservations");
    return ticketRepo.findAll();
  }
}
