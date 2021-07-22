package ru.netcracker.studentsummer2021.goodsmarketplace.dto.valuesCharacter;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LinkToProductDTO {

    private Long id;
    private Long productId;
    private Long valueId;
}
