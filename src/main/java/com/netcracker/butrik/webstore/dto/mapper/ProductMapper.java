package com.netcracker.butrik.webstore.dto.mapper;

import com.netcracker.butrik.webstore.dto.ProductDto;
import com.netcracker.butrik.webstore.model.Product;
import java.util.List;
import org.mapstruct.Mapper;


@Mapper
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product fromDto(ProductDto productDto);

    List<ProductDto> toDtos(List<Product> products);
}
