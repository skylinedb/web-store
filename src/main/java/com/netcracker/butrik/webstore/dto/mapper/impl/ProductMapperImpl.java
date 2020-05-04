package com.netcracker.butrik.webstore.dto.mapper.impl;

import com.netcracker.butrik.webstore.dto.ProductDto;
import com.netcracker.butrik.webstore.dto.mapper.ProductMapper;
import com.netcracker.butrik.webstore.model.Product;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-03T19:23:15+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setProduct_name( product.getProduct_name() );
        productDto.setProduct_price( product.getProduct_price() );

        return productDto;
    }

    @Override
    public Product fromDto(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setProduct_name( productDto.getProduct_name() );
        product.setProduct_price( (int) productDto.getProduct_price() );

        return product;
    }
}
