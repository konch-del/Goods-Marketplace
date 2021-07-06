package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "sales_unit")
public class SalesUnit {

    @Id
    @Column(name = "su_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int quantity;

    @Column
    private float price;

    @Column(name = "shipping_cost")
    private float shipCost;

    @Column(name = "desc_")
    private String desc;

    @Column(name = "date_created")
    private GregorianCalendar dateCreated;

    @Column(name = "modified_date")
    private GregorianCalendar modifiedDate;

    @Id
    @Column(name = "shop_id")
    private Long shop;

    @Id
    @Column(name = "product_id")
    private Long product;
}
