package com.netcracker.butrik.webstore.dto.mapper;

import com.netcracker.butrik.webstore.dto.ContactTypeDto;
import com.netcracker.butrik.webstore.model.ContactType;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ContactTypeMapper {

    ContactTypeDto toDto(ContactType contactType);

    ContactType fromDto(ContactTypeDto contactTypeDto);

    List<ContactTypeDto> toDtos(List<ContactType> contactTypeList);
}
