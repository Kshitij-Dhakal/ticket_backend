package com.kshitij.ticket;

import com.kshitij.ticket.domain.Role;
import com.kshitij.ticket.domain.User;
import com.kshitij.ticket.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class TicketReservationSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(TicketReservationSystemApplication.class, args);
  }

  CommandLineRunner run(UserService userService) {
    return args -> {
      userService.saveRole(new Role(0, "ROLE_USER"));
      userService.saveRole(new Role(0, "ROLE_MANAGER"));
      userService.saveRole(new Role(0, "ROLE_ADMIN"));
      userService.saveRole(new Role(0, "ROLE_SUPER_ADMIN"));

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

      userService.addRoleToUser("dhakalkshitij@gmail.com", "ROLE_USER");
      userService.addRoleToUser("dhakalkshitij@gmail.com", "ROLE_ADMIN");
      userService.addRoleToUser("dhakalkshitij@gmail.com", "ROLE_SUPER_ADMIN");
      userService.addRoleToUser("gurungpawan@gmail.com", "ROLE_USER");
      userService.addRoleToUser("ramkrishnaaacharya@gmail.com", "ROLE_USER");
      userService.addRoleToUser("shresthasanjit@gmail.com", "ROLE_USER");
    };
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
