package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Role;
import com.kshitij.ticket.domain.User;
import com.kshitij.ticket.repo.RoleRepo;
import com.kshitij.ticket.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepo userRepo;
  private final RoleRepo roleRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User byEmail = userRepo.findByEmail(username);
    if (byEmail == null) {
      throw new UsernameNotFoundException("User not found.");
    }
    log.info("User found in the database : {}", username);
    List<SimpleGrantedAuthority> collect =
        byEmail.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(
        byEmail.getEmail(), byEmail.getPassword(), collect);
  }

  @Override
  public User saveUser(@Valid User user) {
    log.info("Saving new user {} to the database", user.getName());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepo.save(user);
  }

  @Override
  public Role saveRole(@Valid Role role) {
    log.info("Saving new role {} to the database", role.getName());
    return roleRepo.save(role);
  }

  @Override
  public void addRoleToUser(
      @NotNull(message = "Email is blank") String email,
      @NotNull(message = "Roll name is blank") String roleName) {
    log.info("Adding role {} to user {}", roleName, email);
    User byEmail = userRepo.findByEmail(email);
    Role byName = roleRepo.findByName(roleName);
    byEmail.getRoles().add(byName);
  }

  @Override
  public User getUser(@NotNull(message = "Email is blank") String email) {
    log.info("Fetching user : {}", email);
    return userRepo.findByEmail(email);
  }

  @Override
  public List<User> getUsers() {
    log.info("Fetching all users");
    return userRepo.findAll();
  }
}
