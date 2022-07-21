package com.kshitij.ticket.repo;

import com.kshitij.ticket.domain.Hall;
import com.kshitij.ticket.domain.HallMovie;
import com.kshitij.ticket.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallMovieRepo extends JpaRepository<HallMovie, Long> {
  HallMovie findByHallAndMovie(Hall hall, Movie movie);
}
