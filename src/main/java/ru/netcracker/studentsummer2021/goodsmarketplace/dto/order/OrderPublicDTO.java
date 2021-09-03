package ru.netcracker.studentsummer2021.goodsmarketplace.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Status;

import java.util.GregorianCalendar;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class OrderPublicDTO {

    private Long id;
    private Long userId;
    private Long suId;
    private Integer mark;
    private GregorianCalendar deliveryDate;
    private String address;
    private String comm;
    private Status status;
    private GregorianCalendar dateCreated;

}
