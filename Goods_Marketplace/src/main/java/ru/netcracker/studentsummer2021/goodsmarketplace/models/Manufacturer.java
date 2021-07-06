package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;

@Entity
@Table
public class Manufacturer {

    @Id
    @Column(name = "manufacturer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "manufacturer_name")
    private String name;

    @Column(name = "desc_")
    private String desc;

    @Column(name = "id_picture")
    private String picture;
}
