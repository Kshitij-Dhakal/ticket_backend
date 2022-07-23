package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Hall;
import com.kshitij.ticket.domain.Show;

import java.util.List;

public interface HallService {
  Hall saveHall(Hall hall);

  Show addShow(Show show);

  List<Show> getAllMovies();

  List<Hall> getAllHalls();

    Show getShowById(long showId);
}
