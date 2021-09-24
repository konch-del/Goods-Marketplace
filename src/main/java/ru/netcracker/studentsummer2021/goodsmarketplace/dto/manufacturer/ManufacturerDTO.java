package ru.netcracker.studentsummer2021.goodsmarketplace.dto.manufacturer;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Data Transfer Object класс
 * Отображает производителя
 */
@Getter
@Setter
@Builder
public class ManufacturerDTO {
    private Long id;
    private String name;
    private String desc;
    private String picture;
}
