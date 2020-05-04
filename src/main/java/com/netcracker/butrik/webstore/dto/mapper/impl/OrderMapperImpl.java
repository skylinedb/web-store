package com.netcracker.butrik.webstore.dto.mapper.impl;

import com.netcracker.butrik.webstore.dto.OrderDto;
import com.netcracker.butrik.webstore.dto.ProductDto;
import com.netcracker.butrik.webstore.dto.UserDto;
import com.netcracker.butrik.webstore.dto.mapper.OrderMapper;
import com.netcracker.butrik.webstore.dto.mapper.ProductMapper;
import com.netcracker.butrik.webstore.model.Order;
import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.ws.soap.Addressing;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-03T19:23:15+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private ProductMapperImpl productMapper;

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( order.getId() );
        orderDto.setAddress( order.getAddress() );
        orderDto.setUserId( order.getUserId() );
        orderDto.setTimestamp( order.getTimestamp() );
        orderDto.setSumm( order.getSumm() );
        orderDto.setSumm_discount( order.getSumm_discount() );
        orderDto.setDiscount_percent( order.getDiscount_percent() );
//        orderDto.setUser( userToUserDto( order.getUser() ) );
        orderDto.setProducts( productListToProductDtoList( order.getProducts() ) );

        return orderDto;
    }

    public OrderDto toDtoWithUser(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( order.getId() );
        orderDto.setAddress( order.getAddress() );
        orderDto.setUserId( order.getUserId() );
        orderDto.setTimestamp( order.getTimestamp() );
        orderDto.setSumm( order.getSumm() );
        orderDto.setSumm_discount( order.getSumm_discount() );
        orderDto.setDiscount_percent( order.getDiscount_percent() );
        orderDto.setUser( userToUserDto( order.getUser() ) );
        orderDto.setProducts( productListToProductDtoList( order.getProducts() ) );

        return orderDto;
    }

    @Override
    public Order toModel(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDto.getId() );
        order.setAddress( orderDto.getAddress() );
        order.setUserId( orderDto.getUserId() );
        order.setTimestamp( orderDto.getTimestamp() );
        order.setSumm( orderDto.getSumm() );
        order.setSumm_discount( orderDto.getSumm_discount() );
        order.setDiscount_percent( orderDto.getDiscount_percent() );
        order.setUser( userDtoToUser( orderDto.getUser() ) );
        order.setProducts( productDtoListToProductList( orderDto.getProducts() ) );

        return order;
    }

    @Override
    public List<OrderDto> toDtos(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( toDto( order ) );
        }

        return list;
    }

    public List<OrderDto> toDtosWithUser(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( toDtoWithUser( order ) );
        }

        return list;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFirst_name( user.getFirst_name() );
        userDto.setLast_name( user.getLast_name() );
        userDto.setEmail( user.getEmail() );
        userDto.setPass( user.getPass() );
        userDto.setDiscount_id( user.getDiscount_id() );
        userDto.setAdmin_toggle( user.isAdmin_toggle() );
//        userDto.setOrders( toDtos( user.getOrders() ) );

        return userDto;
    }

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productMapper.toDto( product ) );
        }

        return list1;
    }

    protected List<Order> orderDtoListToOrderList(List<OrderDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Order> list1 = new ArrayList<Order>( list.size() );
        for ( OrderDto orderDto : list ) {
            list1.add( toModel( orderDto ) );
        }

        return list1;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setFirst_name( userDto.getFirst_name() );
        user.setLast_name( userDto.getLast_name() );
        user.setEmail( userDto.getEmail() );
        user.setPass( userDto.getPass() );
        user.setDiscount_id( userDto.getDiscount_id() );
        user.setAdmin_toggle( userDto.isAdmin_toggle() );
//        user.setOrders( orderDtoListToOrderList( userDto.getOrders() ) );

        return user;
    }

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productMapper.fromDto( productDto ) );
        }

        return list1;
    }
}
