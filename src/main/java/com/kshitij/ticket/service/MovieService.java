package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Movie;

import java.util.List;

public interface MovieService {
  Movie saveMovie(Movie movie);

  List<Movie> getAllMovies();
}
