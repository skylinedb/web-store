package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.DiscountJpaRepository;
import com.netcracker.butrik.webstore.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    DiscountJpaRepository discountJpaRepository;
    @Autowired
    UserJpaRepository userJpaRepository;

    public void saveUser(final User user) {
        user.setAdmin_toggle(false);
        user.setDiscount_id(discountJpaRepository.findByPercent(0));
        userJpaRepository.save(user);
    }

}
