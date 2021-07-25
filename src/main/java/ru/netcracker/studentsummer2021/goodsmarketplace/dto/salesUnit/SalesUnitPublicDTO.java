package ru.netcracker.studentsummer2021.goodsmarketplace.dto.salesUnit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.netcracker.studentsummer2021.goodsmarketplace.dto.product.ProductPublicDTO;
import java.util.GregorianCalendar;

@Setter
@Getter
@Builder
public class SalesUnitPublicDTO {

    private Long id;
    private Long shopId;
    private ProductPublicDTO product;
    private int quantity;
    private float price;
    private float shipCost;
    private String desc;
    private GregorianCalendar dateCreated;
    private GregorianCalendar modifiedDate;

}
