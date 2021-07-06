package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_values_character")
public class ProductValuesCharacter {

    @Id
    @Column(name = "product_id")
    private Long product;

    @Id
    @Column(name = "vc_id")
    private Long value;

    @Id
    @Column(name = "charact_id")
    private Long characteristic;

    @Id
    @Column(name = "category_id")
    private Long category;
}
