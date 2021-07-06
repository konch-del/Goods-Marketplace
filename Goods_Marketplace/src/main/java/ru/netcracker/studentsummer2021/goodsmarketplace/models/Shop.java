package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table
public class Shop {

    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "shop_name")
    private String name;

    @Column(name = "desc_")
    private String desc;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "work_time")
    private String workTime;

    @Column
    private String address;

    @Column(name = "date_create")
    private GregorianCalendar dateCreated;

    @Column(name = "modified_date")
    private GregorianCalendar modifiedDate;
}
