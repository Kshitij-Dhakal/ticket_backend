package com.kshitij.ticket.repo;

import com.kshitij.ticket.domain.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepo extends JpaRepository<Hall, Long> {
  Hall findByName(String name);
}
