package com.netcracker.butrik.webstore.repository;

import com.netcracker.butrik.webstore.model.Contact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ContactJpaRepository extends JpaRepository<Contact, Integer> {
    Contact findById(int id);

    List<Contact> findAll();

    List<Contact> findByUserId(int user_id);
}
