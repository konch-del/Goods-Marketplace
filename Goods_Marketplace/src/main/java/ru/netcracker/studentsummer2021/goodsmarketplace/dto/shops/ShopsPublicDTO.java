package ru.netcracker.studentsummer2021.goodsmarketplace.dto.shops;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


/**
 * Data Transfer Object класс
 * Отображает магазин для пользователя
 */
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class ShopsPublicDTO implements ShopsDTO{
    private Long id;
    private String name;
    private String desc;
    private String email;
    private String phoneNumber;
    private String workTime;
    private String address;
}
