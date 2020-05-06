package com.netcracker.butrik.webstore.dto.mapper;

import com.netcracker.butrik.webstore.dto.ContactDto;
import com.netcracker.butrik.webstore.model.Contact;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ContactMapper {

    @Mapping(target = "type_label", source = "contactType.type")
//    @Mapping(target = "userId", source = "user_id")
//    @Mapping(target = "contactType", ignore = true)
    ContactDto toDto(Contact contact);

    @InheritInverseConfiguration
//    @Mapping(target = "user_id", source = "userId")
    @Mapping(target = "contactType.type", source = "type_label")
    Contact fromDto(ContactDto contactDto);


    List<ContactDto> toDtos(List<Contact> contacts);
}
