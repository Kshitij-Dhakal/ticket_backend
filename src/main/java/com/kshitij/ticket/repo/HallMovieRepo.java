package com.kshitij.ticket.repo;

import com.kshitij.ticket.domain.Hall;
import com.kshitij.ticket.domain.Show;
import com.kshitij.ticket.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallMovieRepo extends JpaRepository<Show, Long> {
  Show findByHallAndMovie(Hall hall, Movie movie);
}
