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
public class ProductAdminDTO extends ProductPublicDTO{

    private GregorianCalendar dateCreation;
    private GregorianCalendar modifiedDate;
}
