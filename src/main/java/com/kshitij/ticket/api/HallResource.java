package com.kshitij.ticket.api;

import com.kshitij.ticket.domain.Hall;
import com.kshitij.ticket.domain.Show;
import com.kshitij.ticket.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HallResource {
  private final HallService hallService;

  @PostMapping("/hall")
  public ResponseEntity<Hall> saveHall(@RequestBody Hall hall) {
    return ResponseEntity.ok(hallService.saveHall(hall));
  }

  @GetMapping("/hall")
  public ResponseEntity<List<Hall>> getAllHalls() {
    return ResponseEntity.ok(hallService.getAllHalls());
  }

  @PostMapping("/hall/{hall-id}/movie")
  public ResponseEntity<Show> saveHallMovie(
      @PathVariable("hall-id") long hallId, @RequestBody Show show) {
    show.setHall(new Hall(hallId, null, 0));
    return ResponseEntity.ok(hallService.addShow(show));
  }

  @GetMapping("/shows")
  public ResponseEntity<List<Show>> getAllMovies(
      @RequestParam(required = false, name = "next") String next) {
    return ResponseEntity.ok(hallService.getAllMovies());
  }

  @GetMapping("/show/{show-id}")
  public ResponseEntity<Show> getShowById(@PathVariable("show-id") long showId) {
    return ResponseEntity.ok(hallService.getShowById(showId));
  }
}
