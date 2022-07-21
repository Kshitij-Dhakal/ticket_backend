package com.kshitij.ticket.repo;

import com.kshitij.ticket.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
  Movie findByName(String name);
}
