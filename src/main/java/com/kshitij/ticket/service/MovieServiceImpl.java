package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Movie;
import com.kshitij.ticket.repo.MovieRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MovieServiceImpl implements MovieService {
  private final MovieRepo movieRepo;

  @Override
  public Movie saveMovie(@Valid Movie movie) {
    return movieRepo.save(movie);
  }

  @Override
  public List<Movie> getAllMovies() {
    return movieRepo.findAll();
  }
}
