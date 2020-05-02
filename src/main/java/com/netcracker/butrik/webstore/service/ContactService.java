package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.model.Contact;
import com.netcracker.butrik.webstore.repository.ContactJpaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactService {

    @Autowired
    ContactJpaRepository contactJpaRepository;

    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    public void save(final Contact contact) {
        contactJpaRepository.save(contact);
        log.info("Contact: ID:"
            +contact.getId()
            +" "+contact.getContactType().getType()
            +" "+contact.getValue()
            +"  SAVE OPERATION");
    }

    public void update(final Contact contact) {
        contactJpaRepository.save(contact);
        log.info("Contact: ID:"
            +contact.getId()
            +" "+contact.getContactType().getType()
            +" "+contact.getValue()
            +"  UPDATE OPERATION");
    }

    public void delete(final Contact contact) {
        contactJpaRepository.delete(contact);
        log.info("Contact: ID:"
            +contact.getId()
            +" "+contact.getContactType().getType()
            +" "+contact.getValue()
            +"  DELETE OPERATION");
    }

    public Contact findById(final int id) {
        log.info("Contact: ID:" + id + "  FindByID OPERATION");
        return contactJpaRepository.findById(id);
    }


    public List<Contact> findAll() {
        log.info("Contact: FindAll OPERATION");
        return contactJpaRepository.findAll();
    }

    public List<Contact> findByUserId(int id) {
        log.info("Contact: ID:" + id + "  FindByUserID OPERATION");
        return contactJpaRepository.findByUserId(id);
    }
}
