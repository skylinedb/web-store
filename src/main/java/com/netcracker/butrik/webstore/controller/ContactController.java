package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.dto.ContactDto;
import com.netcracker.butrik.webstore.dto.ContactTypeDto;
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
    public List<ContactDto> findAll() {
        return contactService.findAll();
    }

    @GetMapping(value = "/findAllTypes")
    public List<ContactTypeDto> findAllTypes() {
        return contactService.findAllTypes();
    }

    @GetMapping(value = "/findById")
    public ContactDto findById(@RequestParam int id) {
        return contactService.findById(id);
    }

    @GetMapping(value = "/findByUserId")
    public List<ContactDto> findByContactId(@RequestParam int id) {
        return contactService.findByUserId(id);
    }

    @PostMapping(value = "/save")
    public ContactDto loadContact(@RequestBody @Valid ContactDto contactDto) {
        contactService.save(contactDto);
        return contactService.findById(contactDto.getId());
    }

    @PutMapping(value = "/update")
    public ContactDto updateContact(@RequestBody @Valid ContactDto contactDto) {
        contactService.update(contactDto);
        return contactService.findById(contactDto.getId());
    }

    @PostMapping(value = "/delete")
    public void deleteContact(@RequestBody ContactDto contactDto) {
        contactService.delete(contactDto);
    }
}
