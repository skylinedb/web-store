package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.dto.DiscountDto;
import com.netcracker.butrik.webstore.dto.mapper.impl.DiscountMapperImpl;
import com.netcracker.butrik.webstore.model.Discount;
import com.netcracker.butrik.webstore.repository.DiscountJpaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountService {
    private static Logger log = LoggerFactory.getLogger(DiscountService.class);
    @Autowired
    private DiscountJpaRepository discountJpaRepository;
    @Autowired
    private DiscountMapperImpl discountMapper;

    public List<DiscountDto> findAll() {
        List<Discount> discountList = discountJpaRepository.findAll();
        return discountMapper.toDtos(discountList);
    }

    public DiscountDto findById(int id) {
        Discount discount = discountJpaRepository.findById(id);
        return discountMapper.toDto(discount);
    }

    public void save(final DiscountDto discountDto) {
        Discount discount = discountMapper.fromDto(discountDto);
        discountJpaRepository.save(discount);
        log.info("Discount: ID:"
            + discount.getId()
            + " " + discount.getBadge()
            + " " + discount.getPercent()
            + " " + discount.getSumm()
            + "  SAVE OPERATION");
    }

    public void update(final DiscountDto discountDto) {
        Discount discount = discountMapper.fromDto(discountDto);
        discountJpaRepository.save(discount);
        log.info("Discount: ID:"
            + discount.getId()
            + " " + discount.getBadge()
            + " " + discount.getPercent()
            + " " + discount.getSumm()
            + "  UPDATE OPERATION");
    }

    public void delete(final DiscountDto discountDto) {
        Discount discount = discountMapper.fromDto(discountDto);
        discountJpaRepository.delete(discount);
        log.info("Discount: ID:"
            + discount.getId()
            + " " + discount.getBadge()
            + " " + discount.getPercent()
            + " " + discount.getSumm()
            + "  DELETE OPERATION");
    }


}
