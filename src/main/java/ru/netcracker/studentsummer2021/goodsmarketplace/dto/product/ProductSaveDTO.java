package ru.netcracker.studentsummer2021.goodsmarketplace.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.GregorianCalendar;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductSaveDTO implements ProductDTO{

    private Long id;
    private Long categoryId;
    private Long manufacturerId;
    private String name;
    private String model;
    private String article;
    private String desc;
    private Double weight;
    private String dimensions;
    private GregorianCalendar dateReleased;
}
