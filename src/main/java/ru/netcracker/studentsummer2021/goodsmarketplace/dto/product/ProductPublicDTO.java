package ru.netcracker.studentsummer2021.goodsmarketplace.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.category.CategoryDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer.ManufacturerDTO;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.PictureProduct;

import java.util.GregorianCalendar;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductPublicDTO implements ProductDTO{

    private Long id;
    private CategoryDTO category;
    private ManufacturerDTO manufacturer;
    private String name;
    private String model;
    private String article;
    private String desc;
    private Double weight;
    private String dimensions;
    private GregorianCalendar dateReleased;
    private PictureProduct picture;
}
