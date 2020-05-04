package com.netcracker.butrik.webstore.dto.mapper;

import com.netcracker.butrik.webstore.dto.ContactDto;
import com.netcracker.butrik.webstore.model.Contact;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {

    ContactDto toDto(Contact contact);

    Contact fromDto(ContactDto contactDto);

    List<ContactDto> toDtos(List<Contact> contacts);
}
