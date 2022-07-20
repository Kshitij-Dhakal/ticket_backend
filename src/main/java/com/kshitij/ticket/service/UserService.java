package com.kshitij.ticket.service;

import com.kshitij.ticket.domain.Role;
import com.kshitij.ticket.domain.User;

import java.util.List;

public interface UserService {
  User saveUser(User user);

  Role saveRole(Role role);

  void addRoleToUser(String email, String roleName);

  User getUser(String email);

  List<User> getUsers();
}
