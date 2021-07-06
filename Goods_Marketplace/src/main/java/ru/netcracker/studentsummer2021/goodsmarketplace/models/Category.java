package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;

@Entity
@Table(name = "category_")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "parent_id")
    private Long parentID;

    @Column(name = "category_name")
    private String name;
}
