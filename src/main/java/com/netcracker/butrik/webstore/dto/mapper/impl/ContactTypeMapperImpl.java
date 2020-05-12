package com.netcracker.butrik.webstore.dto.mapper.impl;

import com.netcracker.butrik.webstore.dto.ContactDto;
import com.netcracker.butrik.webstore.dto.ContactTypeDto;
import com.netcracker.butrik.webstore.dto.mapper.ContactTypeMapper;
import com.netcracker.butrik.webstore.model.Contact;
import com.netcracker.butrik.webstore.model.ContactType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-03T19:23:15+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class ContactTypeMapperImpl implements ContactTypeMapper {

    @Override
    public ContactTypeDto toDto(ContactType contactType) {
        if (contactType == null) {
            return null;
        }

        ContactTypeDto contactTypeDto = new ContactTypeDto();

        contactTypeDto.setId(contactType.getId());
        contactTypeDto.setType(contactType.getType());

        return contactTypeDto;
    }

    @Override
    public ContactType fromDto(ContactTypeDto contactTypeDto) {
        if (contactTypeDto == null) {
            return null;
        }

        ContactType contactType = new ContactType();

        contactType.setId(contactTypeDto.getId());
        contactType.setType(contactTypeDto.getType());

        return contactType;
    }

    @Override
    public List<ContactTypeDto> toDtos(List<ContactType> contacts) {
        if ( contacts == null ) {
            return null;
        }

        List<ContactTypeDto> list = new ArrayList<ContactTypeDto>( contacts.size() );
        for ( ContactType contact : contacts ) {
            list.add( toDto( contact ) );
        }

        return list;
    }
}
