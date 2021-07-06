package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;

@Entity
@Table
public class Characteristic {

    @Id
    @Column(name = "charact_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "character_name")
    private String name;

    @Column(name = "type_character")
    private String type;

    @Id
    @Column(name = "category_id")
    private Long categoryId;
}
