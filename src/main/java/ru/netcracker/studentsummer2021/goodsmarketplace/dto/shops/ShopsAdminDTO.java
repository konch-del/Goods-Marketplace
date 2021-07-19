package ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.GregorianCalendar;


/**
 * Data Transfer Object класс
 * Отображает магазин для администратора
 */
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class ShopsAdminDTO extends ShopsPublicDTO implements ShopsDTO{
    private GregorianCalendar dateCreated;
    private GregorianCalendar modifiedDate;
}
