package com.netcracker.butrik.webstore.dto.mapper.impl;

import com.netcracker.butrik.webstore.dto.ContactDto;
import com.netcracker.butrik.webstore.dto.mapper.ContactMapper;
import com.netcracker.butrik.webstore.model.Contact;
import com.netcracker.butrik.webstore.model.ContactType;
import com.netcracker.butrik.webstore.model.User;
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
public class ContactMapperImpl implements ContactMapper {

    @Override
    public ContactDto toDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        contactDto.setUserId( contact.getUser_id() );
        contactDto.setType_label( contactContactTypeType( contact ) );
        contactDto.setId( contact.getId() );
        contactDto.setValue( contact.getValue() );

        return contactDto;
    }

    @Override
    public Contact fromDto(ContactDto contactDto) {
        if ( contactDto == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setContactType( contactDtoToContactType( contactDto ) );
        contact.setUser_id( contactDto.getUserId() );
        contact.setUser(userToFromDto(contactDto.getUserId()));
        contact.setId( contactDto.getId() );
        contact.setValue( contactDto.getValue() );

        return contact;
    }

    @Override
    public List<ContactDto> toDtos(List<Contact> contacts) {
        if ( contacts == null ) {
            return null;
        }

        List<ContactDto> list = new ArrayList<ContactDto>( contacts.size() );
        for ( Contact contact : contacts ) {
            list.add( toDto( contact ) );
        }

        return list;
    }

    private String contactContactTypeType(Contact contact) {
        if ( contact == null ) {
            return null;
        }
        ContactType contactType = contact.getContactType();
        if ( contactType == null ) {
            return null;
        }
        String type = contactType.getType();
        if ( type == null ) {
            return null;
        }
        return type;
    }

    protected ContactType contactDtoToContactType(ContactDto contactDto) {
        if ( contactDto == null ) {
            return null;
        }

        ContactType contactType = new ContactType();

        contactType.setType( contactDto.getType_label() );

        return contactType;
    }

    protected User userToFromDto(int id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}
