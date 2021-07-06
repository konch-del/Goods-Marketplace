package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;

@Entity
@Table(name = "values_character")
public class ValuesCharacter {

    @Id
    @Column(name = "vc_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "val")
    private String value;

    @Id
    @Column(name = "charact_id")
    private Long charact;

    @Id
    @Column(name = "category_id")
    private Long category;
}
