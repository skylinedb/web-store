package com.netcracker.butrik.webstore.dto.mapper.impl;

import com.netcracker.butrik.webstore.dto.DiscountDto;
import com.netcracker.butrik.webstore.dto.mapper.DiscountMapper;
import com.netcracker.butrik.webstore.model.Discount;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-12T20:49:00+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class DiscountMapperImpl implements DiscountMapper {

    @Override
    public DiscountDto toDto(Discount contactType) {
        if ( contactType == null ) {
            return null;
        }

        DiscountDto discountDto = new DiscountDto();

        discountDto.setId( contactType.getId() );
        discountDto.setBadge( contactType.getBadge() );
        discountDto.setPercent( contactType.getPercent() );
        discountDto.setSumm( contactType.getSumm() );

        return discountDto;
    }

    @Override
    public Discount fromDto(DiscountDto contactTypeDto) {
        if ( contactTypeDto == null ) {
            return null;
        }

        Discount discount = new Discount();

        discount.setId( contactTypeDto.getId() );
        discount.setBadge( contactTypeDto.getBadge() );
        discount.setPercent( contactTypeDto.getPercent() );
        discount.setSumm( contactTypeDto.getSumm() );

        return discount;
    }

    @Override
    public List<DiscountDto> toDtos(List<Discount> contactTypeList) {
        if ( contactTypeList == null ) {
            return null;
        }

        List<DiscountDto> list = new ArrayList<DiscountDto>( contactTypeList.size() );
        for ( Discount discount : contactTypeList ) {
            list.add( toDto( discount ) );
        }

        return list;
    }
}
