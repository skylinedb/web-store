package com.netcracker.butrik.webstore.repository;

import com.netcracker.butrik.webstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserJpaRepository extends JpaRepository<User, Integer> {
}
