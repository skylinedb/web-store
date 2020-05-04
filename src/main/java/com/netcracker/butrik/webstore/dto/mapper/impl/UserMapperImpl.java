package com.netcracker.butrik.webstore.dto.mapper.impl;

import com.netcracker.butrik.webstore.dto.OrderDto;
import com.netcracker.butrik.webstore.dto.UserDto;
import com.netcracker.butrik.webstore.dto.mapper.UserMapper;
import com.netcracker.butrik.webstore.model.Order;
import com.netcracker.butrik.webstore.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-03T19:23:15+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private OrderMapperImpl orderMapper;
    @Autowired
    private ContactMapperImpl contactMapper;

    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

//        userDto.setOrders( orderMapper.toDtos( user.getOrders() ) );
        userDto.setId(user.getId());
        userDto.setFirst_name(user.getFirst_name());
        userDto.setLast_name(user.getLast_name());
        userDto.setEmail(user.getEmail());
        userDto.setPass(user.getPass());
        userDto.setDiscount_id(user.getDiscount_id());
        userDto.setAdmin_toggle(user.isAdmin_toggle());
        userDto.setContacts(contactMapper.toDtos(user.getContacts()));

        return userDto;
    }

    public UserDto toDtoWithOrders(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setOrders(orderMapper.toDtos(user.getOrders()));
        userDto.setId(user.getId());
        userDto.setFirst_name(user.getFirst_name());
        userDto.setLast_name(user.getLast_name());
        userDto.setEmail(user.getEmail());
        userDto.setPass(user.getPass());
        userDto.setDiscount_id(user.getDiscount_id());
        userDto.setAdmin_toggle(user.isAdmin_toggle());

        return userDto;
    }

    @Override
    public User fromDto(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();

        user.setOrders(orderDtoListToOrderList(userDto.getOrders()));
        user.setId(userDto.getId());
        user.setFirst_name(userDto.getFirst_name());
        user.setLast_name(userDto.getLast_name());
        user.setEmail(userDto.getEmail());
        user.setPass(userDto.getPass());
        user.setDiscount_id(userDto.getDiscount_id());
        user.setAdmin_toggle(userDto.isAdmin_toggle());

        return user;
    }

    @Override
    public List<UserDto> toDtosWithOrders(List<User> users) {
        if (users == null) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>(users.size());
        for (User user : users) {
            list.add(toDtoWithOrders(user));
        }

        return list;
    }

    public List<UserDto> toDtos(List<User> users) {
        if (users == null) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>(users.size());
        for (User user : users) {
            list.add(toDto(user));
        }

        return list;
    }


    protected List<Order> orderDtoListToOrderList(List<OrderDto> list) {
        if (list == null) {
            return null;
        }

        List<Order> list1 = new ArrayList<Order>(list.size());
        for (OrderDto orderDto : list) {
            list1.add(orderMapper.toModel(orderDto));
        }

        return list1;
    }
}
