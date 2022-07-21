package com.kshitij.ticket;

import com.kshitij.ticket.domain.Hall;
import com.kshitij.ticket.domain.Movie;
import com.kshitij.ticket.domain.Role;
import com.kshitij.ticket.domain.User;
import com.kshitij.ticket.service.HallService;
import com.kshitij.ticket.service.MovieService;
import com.kshitij.ticket.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

import static com.kshitij.ticket.domain.Role.ROLE_ADMIN;
import static com.kshitij.ticket.domain.Role.ROLE_USER;

@Configuration
public class PopulateDb {
  CommandLineRunner run(
      UserService userService, MovieService movieService, HallService hallService) {
    return args -> {
      userService.saveRole(new Role(0, ROLE_ADMIN));
      userService.saveRole(new Role(0, ROLE_USER));

      userService.saveUser(
          new User(0, "Kshitij Dhakal", "dhakalkshitij@gmail.com", "password", new ArrayList<>()));
      userService.saveUser(
          new User(0, "Pawan Gurung", "gurungpawan@gmail.com", "password", new ArrayList<>()));
      userService.saveUser(
          new User(
              0,
              "Ram Krishna Aacharya",
              "ramkrishnaaacharya@gmail.com",
              "password",
              new ArrayList<>()));
      userService.saveUser(
          new User(
              0, "Sanjit Shrestha", "shresthasanjit@gmail.com", "password", new ArrayList<>()));

      userService.addRoleToUser("dhakalkshitij@gmail.com", ROLE_USER);
      userService.addRoleToUser("dhakalkshitij@gmail.com", ROLE_ADMIN);
      userService.addRoleToUser("gurungpawan@gmail.com", ROLE_USER);
      userService.addRoleToUser("ramkrishnaaacharya@gmail.com", ROLE_USER);
      userService.addRoleToUser("shresthasanjit@gmail.com", ROLE_USER);

      movieService.saveMovie(
          new Movie(
              0,
              "The Shawshank Redemption",
              "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY209_CR3,0,140,209_AL_.jpg"));
      movieService.saveMovie(
          new Movie(
              0,
              "The Godfather",
              "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY209_CR0,0,140,209_AL_.jpg"));
      movieService.saveMovie(
          new Movie(
              0,
              "Schindler's List",
              "https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX140_CR0,0,140,209_AL_.jpg"));
      movieService.saveMovie(
          new Movie(
              0,
              "Raging Bull ",
              "https://m.media-amazon.com/images/M/MV5BYjRmODkzNDItMTNhNi00YjJlLTg0ZjAtODlhZTM0YzgzYThlXkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_UY209_CR1,0,140,209_AL_.jpg"));

      hallService.saveHall(new Hall(0, "Jalma Hall", 100));
      hallService.saveHall(new Hall(0, "Ganesh Hall", 100));
      hallService.saveHall(new Hall(0, "Indra Hall", 100));
    };
  }
}
