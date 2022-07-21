package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Hall;
import com.kshitij.ticket.domain.HallMovie;
import com.kshitij.ticket.domain.Movie;
import com.kshitij.ticket.error.NotFoundException;
import com.kshitij.ticket.repo.HallMovieRepo;
import com.kshitij.ticket.repo.HallRepo;
import com.kshitij.ticket.repo.MovieRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HallServiceImpl implements HallService {
  private final HallRepo hallRepo;
  private final MovieRepo movieRepo;
  private final HallMovieRepo hallMovieRepo;

  @Override
  public Hall saveHall(@Valid Hall hall) {
    log.info("Saving hall : {}", hall);
    return hallRepo.save(hall);
  }

  @Override
  public HallMovie addMovie(@Valid HallMovie hallMovie) {
    log.info("Adding movie in hall : {}", hallMovie);
    Optional<Hall> hall = hallRepo.findById(hallMovie.getHall().getId());
    if (hall.isEmpty()) {
      throw new NotFoundException("Hall not found");
    }
    Optional<Movie> movie = movieRepo.findById(hallMovie.getMovie().getId());
    if (movie.isEmpty()) {
      throw new NotFoundException("Movie not found");
    }
    hallMovie.setHall(hall.get());
    hallMovie.setMovie(movie.get());
    return hallMovieRepo.save(hallMovie);
  }

  @Override
  public List<HallMovie> getAllMovies() {
    log.info("Fetching hall movies.");
    return hallMovieRepo.findAll();
  }

  @Override
  public List<Hall> getAllHalls() {
    log.info("Fetching all halls.");
    return hallRepo.findAll();
  }
}
