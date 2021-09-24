package ru.netcracker.studentsummer2021.goodsmarketplace.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.util.GregorianCalendar;


@Getter
@Setter
public class PictureProductDTO {

    private Long id;
    private Long productId;
    private GregorianCalendar dateCreated;
}
