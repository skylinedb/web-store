package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.dto.ContactDto;
import com.netcracker.butrik.webstore.dto.UserDto;
import com.netcracker.butrik.webstore.dto.mapper.impl.UserMapperImpl;
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
    private DiscountJpaRepository discountJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserMapperImpl userMapper;
    @Autowired
    private ContactService contactService;

    public void save(final UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        user.setAdmin_toggle(false);
        user.setDiscount_id(discountJpaRepository.findByPercent(0));
        userJpaRepository.save(user);
        User saveUser = userJpaRepository.findByEmail(user.getEmail());
        ContactDto contactDto = new ContactDto("Email", saveUser.getEmail(), saveUser.getId());
        contactService.save(contactDto);
        log.info("User: ID:"
            + user.getId()
            + " " + user.getFirst_name()
            + " " + user.getLast_name()
            + "  SAVE OPERATION");
    }

    public void update(final UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        userJpaRepository.save(user);
        log.info("User: ID:"
            + user.getId()
            + " " + user.getFirst_name()
            + " " + user.getLast_name()
            + "  UPDATE OPERATION");
    }

    public void delete(final UserDto userDto) {
        User user = userMapper.fromDto(userDto);
        userJpaRepository.delete(user);
        log.info("User: ID:"
            + user.getId() + " "
            + user.getFirst_name() + " "
            + user.getLast_name()
            + "  DELETE OPERATION");
    }

    public UserDto findById(final int id) {
        log.info("User: ID:" + id + "  FindByID OPERATION");
        User user=userJpaRepository.findById(id);
        return userMapper.toDto(user);
    }

    public UserDto findById_withOrders(final int id) {
        log.info("User: ID:" + id + "  FindByID OPERATION");
        User user=userJpaRepository.findById(id);
        return userMapper.toDtoWithOrders(user);
    }

    public UserDto findByEmail(final String email) {
        log.info("User: EMAIL:" + email + "  FindByEMAIL OPERATION");
        User user=userJpaRepository.findByEmail(email);
        return userMapper.toDto(user);
    }

    public UserDto findByEmail_withOrders(final String email) {
        log.info("User: EMAIL:" + email + "  FindByEMAIL OPERATION");
        User user=userJpaRepository.findByEmail(email);
        return userMapper.toDtoWithOrders(user);
    }

    public List<UserDto> findAll() {
        log.info("User: FindAll OPERATION");
        List<User> users = userJpaRepository.findAll();
        return userMapper.toDtos(users);
    }

    public List<UserDto> findAll_withOrders() {
        log.info("User: FindAll OPERATION");
        List<User> users = userJpaRepository.findAll();
        return userMapper.toDtosWithOrders(users);
    }

}
