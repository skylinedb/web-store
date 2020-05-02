package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.model.Contact;
import com.netcracker.butrik.webstore.service.ContactService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${requestMapping.contact}")
@CrossOrigin(origins = "${cors.frontend.url}")
public class ContactController {

    @Autowired
    ContactService contactService;


    @GetMapping(value = "/findAll")
    public List<Contact> findAll() {
        return contactService.findAll();
    }

    @GetMapping(value = "/findById")
    public Contact findById(@RequestParam int id) {
        return contactService.findById(id);
    }

    @GetMapping(value = "/findByUserId")
    public List<Contact> findByContactId(@RequestParam int id) {
        return contactService.findByUserId(id);
    }

    @PostMapping(value = "/save")
    public Contact loadContact(@RequestBody @Valid Contact contact) {
        contactService.save(contact);
        return contactService.findById(contact.getId());
    }

    @PutMapping(value = "/update")
    public Contact updateContact(@RequestBody @Valid Contact contact) {
        contactService.update(contact);
        return contactService.findById(contact.getId());
    }

    @PostMapping(value = "/delete")
    public void deleteContact(@RequestBody Contact contact) {
        contactService.delete(contact);
    }
}
