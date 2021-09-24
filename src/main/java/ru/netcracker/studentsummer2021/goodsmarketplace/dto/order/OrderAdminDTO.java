package ru.netcracker.studentsummer2021.goodsmarketplace.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.GregorianCalendar;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OrderAdminDTO extends OrderPublicDTO{

    private GregorianCalendar modifiedDate;
    private GregorianCalendar modDateOS;

}
