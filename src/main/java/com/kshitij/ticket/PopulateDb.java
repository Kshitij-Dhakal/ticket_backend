package com.kshitij.ticket;

import com.kshitij.ticket.domain.*;
import com.kshitij.ticket.repo.DataChangelogRepo;
import com.kshitij.ticket.service.HallService;
import com.kshitij.ticket.service.MovieService;
import com.kshitij.ticket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kshitij.ticket.domain.Role.ROLE_ADMIN;
import static com.kshitij.ticket.domain.Role.ROLE_USER;

@Configuration
@Slf4j
public class PopulateDb {

  @Bean
  CommandLineRunner run(
      UserService userService,
      MovieService movieService,
      HallService hallService,
      DataChangelogRepo dataChangelogRepo) {
    return args -> {
      List<DataChangelog> all = dataChangelogRepo.findAll();
      if (all.isEmpty()) {
        log.info("Populating database");
        dataChangelogRepo.save(new DataChangelog());

        userService.saveRole(new Role(0, ROLE_ADMIN));
        userService.saveRole(new Role(0, ROLE_USER));

        userService.saveUser(
            new User(
                0, "Kshitij Dhakal", "dhakalkshitij@gmail.com", "password", new ArrayList<>()));
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

        Movie tsr =
            movieService.saveMovie(
                new Movie(
                    0,
                    "The Shawshank Redemption",
                    "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY209_CR3,0,140,209_AL_.jpg"));
        Movie gf =
            movieService.saveMovie(
                new Movie(
                    0,
                    "The Godfather",
                    "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UY209_CR0,0,140,209_AL_.jpg"));
        Movie sl =
            movieService.saveMovie(
                new Movie(
                    0,
                    "Schindler's List",
                    "https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX140_CR0,0,140,209_AL_.jpg"));
        Movie rb =
            movieService.saveMovie(
                new Movie(
                    0,
                    "Raging Bull ",
                    "https://m.media-amazon.com/images/M/MV5BYjRmODkzNDItMTNhNi00YjJlLTg0ZjAtODlhZTM0YzgzYThlXkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_UY209_CR1,0,140,209_AL_.jpg"));

        Hall jalma = hallService.saveHall(new Hall(0, "Jalma Hall", 50));
        Hall ganesh = hallService.saveHall(new Hall(0, "Ganesh Hall", 50));
        Hall indra = hallService.saveHall(new Hall(0, "Indra Hall", 50));

        hallService.addShow(new Show(0, jalma, tsr, getDate(2), new ArrayList<>()));
        hallService.addShow(new Show(0, jalma, gf, getDate(3), new ArrayList<>()));
        hallService.addShow(new Show(0, jalma, sl, getDate(4), new ArrayList<>()));
        hallService.addShow(new Show(0, jalma, rb, getDate(5), new ArrayList<>()));
        hallService.addShow(new Show(0, ganesh, tsr, getDate(3), new ArrayList<>()));
        hallService.addShow(new Show(0, ganesh, gf, getDate(2), new ArrayList<>()));
        hallService.addShow(new Show(0, ganesh, sl, getDate(5), new ArrayList<>()));
        hallService.addShow(new Show(0, ganesh, rb, getDate(4), new ArrayList<>()));
        hallService.addShow(new Show(0, indra, tsr, getDate(5), new ArrayList<>()));
        hallService.addShow(new Show(0, indra, gf, getDate(4), new ArrayList<>()));
        hallService.addShow(new Show(0, indra, sl, getDate(3), new ArrayList<>()));
        hallService.addShow(new Show(0, indra, rb, getDate(2), new ArrayList<>()));
      } else {
        log.info("Database already populated");
      }
    };
  }

  private Date getDate(long days) {
    return new Date(System.currentTimeMillis() + days * 864000000);
  }
}
