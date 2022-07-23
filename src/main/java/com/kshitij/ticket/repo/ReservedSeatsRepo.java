package com.kshitij.ticket.repo;

import com.kshitij.ticket.domain.ReservedSeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedSeatsRepo extends JpaRepository<ReservedSeats, Long> {
}
