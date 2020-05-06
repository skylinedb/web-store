package com.netcracker.butrik.webstore.repository;

import com.netcracker.butrik.webstore.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ContactTypeJpaRepository extends JpaRepository<ContactType, Integer> {
    ContactType findByType(String type);
}
