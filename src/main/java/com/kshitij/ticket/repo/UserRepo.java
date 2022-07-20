package com.kshitij.ticket.repo;

import com.kshitij.ticket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
