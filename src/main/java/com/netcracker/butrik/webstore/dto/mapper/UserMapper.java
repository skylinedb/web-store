package com.netcracker.butrik.webstore.dto.mapper;

import com.netcracker.butrik.webstore.dto.UserDto;
import com.netcracker.butrik.webstore.model.User;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(uses = {OrderMapper.class,ContactMapper.class})
public interface UserMapper {

    UserDto toDto(User user);

    @InheritInverseConfiguration
    User fromDto(UserDto userDto);

    List<UserDto> toDtosWithOrders(List<User> users);

}
