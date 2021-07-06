package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "order_")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "delivery_mark")
    private int mark;

    @Column(name = "delivery_date")
    private GregorianCalendar deliveryDate;

    @Column
    private String address;

    @Column
    private String comm;

    @Column(name = "order_status")
    private String status;

    @Column(name = "date_created")
    private GregorianCalendar dateCreated;

    @Column(name = "modified_date")
    private GregorianCalendar modifiedDate;

    @Column(name = "modified_date_os")
    private GregorianCalendar modDateOS;

    @Column(name = "user_id")
    private Long user;

    @Column(name = "su_id")
    private Long su;

    @Column(name = "shop_id")
    private Long shop;

    @Column(name = "product_id")
    private Long product;
}
