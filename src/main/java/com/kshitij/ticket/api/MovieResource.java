package com.kshitij.ticket.api;

import com.kshitij.ticket.domain.Movie;
import com.kshitij.ticket.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieResource {
  private final MovieService movieService;

  @GetMapping("/movies")
  public ResponseEntity<List<Movie>> getAllMovies() {
    return ResponseEntity.ok(movieService.getAllMovies());
  }

  @PostMapping("/movie")
  public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
    return ResponseEntity.ok(movieService.saveMovie(movie));
  }
}
