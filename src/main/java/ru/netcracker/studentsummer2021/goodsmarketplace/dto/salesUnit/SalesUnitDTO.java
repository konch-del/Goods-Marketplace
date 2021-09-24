package ru.netcracker.studentsummer2021.goodsmarketplace.dto.salesUnit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.GregorianCalendar;

@Getter
@Setter
@Builder
public class SalesUnitDTO {

    private Long id;
    private Long shopId;
    private Long productId;
    private int quantity;
    private float price;
    private float shipCost;
    private String desc;
    private GregorianCalendar dateCreated;
    private GregorianCalendar modifiedDate;

}
