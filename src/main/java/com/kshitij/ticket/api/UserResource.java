package com.kshitij.ticket.api;

import com.kshitij.ticket.domain.Role;
import com.kshitij.ticket.domain.User;
import com.kshitij.ticket.dto.UserRole;
import com.kshitij.ticket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @PostMapping("/user")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    URI uri =
        URI.create(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(user));
  }

  @PostMapping("/role")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri =
        URI.create(
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role").toUriString());
    return ResponseEntity.created(uri).body(userService.saveRole(role));
  }

  @PatchMapping("/user/role")
  public ResponseEntity<?> saveRole(@RequestBody UserRole userRole) {
    userService.addRoleToUser(userRole.getEmail(), userRole.getEmail());
    return ResponseEntity.ok().build();
  }
}
