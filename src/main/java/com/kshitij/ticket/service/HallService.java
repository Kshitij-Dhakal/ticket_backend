package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Hall;
import com.kshitij.ticket.domain.HallMovie;

import java.util.List;

public interface HallService {
  Hall saveHall(Hall hall);

  HallMovie addMovie(HallMovie hallMovie);

  List<HallMovie> getAllMovies();

  List<Hall> getAllHalls();

    HallMovie getShowById(long showId);
}
