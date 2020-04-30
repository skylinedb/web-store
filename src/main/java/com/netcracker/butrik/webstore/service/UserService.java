package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.DiscountJpaRepository;
import com.netcracker.butrik.webstore.repository.UserJpaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    DiscountJpaRepository discountJpaRepository;
    @Autowired
    UserJpaRepository userJpaRepository;

    public void save(final User user) {
        user.setAdmin_toggle(false);
        user.setDiscount_id(discountJpaRepository.findByPercent(0));
        userJpaRepository.save(user);
        log.info("User: ID:"
            + user.getId()
            + " " + user.getFirst_name()
            + " " + user.getLast_name()
            + "  SAVE OPERATION");
    }

    public void update(final User user) {
        userJpaRepository.save(user);
        log.info("User: ID:"
            + user.getId()
            + " " + user.getFirst_name()
            + " " + user.getLast_name()
            + "  UPDATE OPERATION");
    }

    public void delete(final User user) {
        userJpaRepository.delete(user);
        log.info("User: ID:"
            + user.getId() + " "
            + user.getFirst_name() + " "
            + user.getLast_name()
            + "  DELETE OPERATION");
    }

    public User findById(final int id) {
        log.info("User: ID:" + id + "  FindByID OPERATION");
        return userJpaRepository.findById(id);
    }

    public User findByEmail(final String email) {
        log.info("User: EMAIL:" + email + "  FindByEMAIL OPERATION");
        return userJpaRepository.findByEmail(email);
    }

    public List<User> findAll() {
        log.info("User: FindAll OPERATION");
        return userJpaRepository.findAll();
    }
}
