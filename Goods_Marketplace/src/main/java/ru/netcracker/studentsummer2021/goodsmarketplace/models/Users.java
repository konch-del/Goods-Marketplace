package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "users_")
public class Users {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_type")
    private String accountType;

    @Column
    private String username;

    @Column(name = "pass")
    private String password;

    @Column
    private String fcs;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_creation")
    private GregorianCalendar dateOfCreation;

    @Column(name = "last_login_date")
    private GregorianCalendar lastLoginDate;

    @Column
    private boolean activation;

    @Column(name = "shop_id")
    private Long shopId;
}
