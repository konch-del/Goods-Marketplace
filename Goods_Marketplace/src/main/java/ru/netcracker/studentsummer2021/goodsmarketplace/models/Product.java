package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_model")
    private String model;

    @Column
    private String article;

    @Column(name = "desc_")
    private String desc;

    @Column
    private String weight;

    @Column
    private String deminsions;

    @Column(name = "date_released")
    private GregorianCalendar dateReleased;

    @Column(name = "date_creation")
    private GregorianCalendar dateCreated;

    @Column(name = "modified_date")
    private GregorianCalendar modifiedDate;

    @Column(name = "category_id")
    private Long category;

    @Column(name = "manufacturer_id")
    private Long manufacturer;
}
