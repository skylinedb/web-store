package com.netcracker.butrik.webstore.dto.mapper;


import com.netcracker.butrik.webstore.dto.DiscountDto;
import com.netcracker.butrik.webstore.model.Discount;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface DiscountMapper {

    DiscountDto toDto(Discount contactType);

    Discount fromDto(DiscountDto contactTypeDto);

    List<DiscountDto> toDtos(List<Discount> contactTypeList);
}
