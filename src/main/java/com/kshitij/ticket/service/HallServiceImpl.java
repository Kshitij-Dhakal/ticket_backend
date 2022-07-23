package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Hall;
import com.kshitij.ticket.domain.Show;
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
  public Show addShow(@Valid Show show) {
    log.info("Adding movie in hall : {}", show);
    Optional<Hall> hall = hallRepo.findById(show.getHall().getId());
    if (hall.isEmpty()) {
      throw new NotFoundException("Hall not found");
    }
    Optional<Movie> movie = movieRepo.findById(show.getMovie().getId());
    if (movie.isEmpty()) {
      throw new NotFoundException("Movie not found");
    }
    show.setHall(hall.get());
    show.setMovie(movie.get());
    return hallMovieRepo.save(show);
  }

  @Override
  public List<Show> getAllMovies() {
    log.info("Fetching hall movies.");
    return hallMovieRepo.findAll();
  }

  @Override
  public List<Hall> getAllHalls() {
    log.info("Fetching all halls.");
    return hallRepo.findAll();
  }

  @Override
  public Show getShowById(long showId) {
    Optional<Show> show = hallMovieRepo.findById(showId);
    if (show.isEmpty()) {
      throw new NotFoundException("Show not found");
    }
    return show.get();
  }
}
