package ru.netcracker.studentsummer2021.goodsmarketplace.dto.characteristic;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CharacteristicDTO {

    private Long id;

    private Long categoryId;

    private String name;

    private String type;
}
