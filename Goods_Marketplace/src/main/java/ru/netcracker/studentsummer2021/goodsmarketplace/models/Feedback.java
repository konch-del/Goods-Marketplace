package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "feedback_")
public class Feedback {

    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private boolean visibility;

    @Column(name = "text_fb")
    private String text;

    @Column
    private int rating;

    @Column(name = "date_created")
    private GregorianCalendar dateCreated;

    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "order_id")
    private Long orderId;
}
