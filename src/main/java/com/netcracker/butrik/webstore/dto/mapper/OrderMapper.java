package com.netcracker.butrik.webstore.dto.mapper;

import com.netcracker.butrik.webstore.dto.OrderDto;
import com.netcracker.butrik.webstore.model.Order;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(uses = {UserMapper.class,ProductMapper.class})
public interface OrderMapper {

    OrderDto toDto(Order order);

    @InheritInverseConfiguration
    Order toModel(OrderDto orderDto);

    List<OrderDto> toDtos(List<Order> orders);
}
