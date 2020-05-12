package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.dto.ContactDto;
import com.netcracker.butrik.webstore.dto.ContactTypeDto;
import com.netcracker.butrik.webstore.dto.mapper.impl.ContactMapperImpl;
import com.netcracker.butrik.webstore.dto.mapper.impl.ContactTypeMapperImpl;
import com.netcracker.butrik.webstore.dto.mapper.impl.UserMapperImpl;
import com.netcracker.butrik.webstore.model.Contact;
import com.netcracker.butrik.webstore.model.ContactType;
import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.ContactJpaRepository;
import com.netcracker.butrik.webstore.repository.ContactTypeJpaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactService {

    private static Logger log = LoggerFactory.getLogger(ContactService.class);
    @Autowired
    ContactJpaRepository contactJpaRepository;
    @Autowired
    ContactMapperImpl contactMapper;
    @Autowired
    UserService userService;
    @Autowired
    UserMapperImpl userMapper;
    @Autowired
    ContactTypeJpaRepository contactTypeJpaRepository;
    @Autowired
    ContactTypeMapperImpl contactTypeMapper;

    public void save(final ContactDto contactDto) {
        ContactType contactType = new ContactType();
        if (contactTypeJpaRepository.findByType(contactDto.getType_label()) == null) {
            contactType.setType(contactDto.getType_label());
            contactTypeJpaRepository.save(contactType);
        } else {
            contactType = contactTypeJpaRepository.findByType(contactDto.getType_label());
        }

        Contact contact = contactMapper.fromDto(contactDto);
        ContactTypeDto contactTypeDto = contactTypeMapper.toDto(contactType);
        User user = userMapper.fromDto(userService.findById(contact.getUser().getId()));
        contact.setContactType(contactTypeMapper.fromDto(contactTypeDto));
        contact.setUser(user);
        contactJpaRepository.save(contact);
        log.info("Contact: ID:"
            + contact.getId()
            + " " + contact.getContactType().getType()
            + " " + contact.getValue()
            + "  SAVE OPERATION");
    }

    public void update(final ContactDto contactDto) {
        Contact contact = contactMapper.fromDto(contactDto);
        contactJpaRepository.save(contact);
        log.info("Contact: ID:"
            + contact.getId()
            + " " + contact.getContactType().getType()
            + " " + contact.getValue()
            + "  UPDATE OPERATION");
    }

    public void delete(final ContactDto contactDto) {
        Contact contact = contactMapper.fromDto(contactDto);
        contactJpaRepository.delete(contact);
        log.info("Contact: ID:"
            + contact.getId()
            + " " + contact.getContactType().getType()
            + " " + contact.getValue()
            + "  DELETE OPERATION");
    }

    public ContactDto findById(final int id) {
        log.info("Contact: ID:" + id + "  FindByID OPERATION");
        Contact contact = contactJpaRepository.findById(id);
        return contactMapper.toDto(contact);
    }


    public List<ContactDto> findAll() {
        log.info("Contact: FindAll OPERATION");
        List<Contact> contacts = contactJpaRepository.findAll();
        return contactMapper.toDtos(contacts);
    }

    public List<ContactTypeDto> findAllTypes() {
        log.info("Contact: FindAll OPERATION");
        List<ContactType> contactTypes = contactTypeJpaRepository.findAll();
        return contactTypeMapper.toDtos(contactTypes);
    }


    public List<ContactDto> findByUserId(int id) {
        log.info("Contact: ID:" + id + "  FindByUserID OPERATION");
        List<Contact> contacts = contactJpaRepository.findByUserId(id);
        return contactMapper.toDtos(contacts);
    }
}
