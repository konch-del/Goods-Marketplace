package ru.netcracker.studentsummer2021.goodsmarketplace.dto.feedback;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.GregorianCalendar;

@Getter
@Setter
@Builder
public class FeedbackDTO {

    private Long id;
    private Long orderId;
    private Long userId;
    private Long productId;
    private Integer visibility;
    private String text;
    private Integer rating;
    private GregorianCalendar dateCreated;

}
