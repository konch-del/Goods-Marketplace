package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "picture_product")
public class PictureProduct {

    @Id
    @Column(name = "pp_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "picture_id")
    private Long picture;

    @Column(name = "date_created")
    private GregorianCalendar dateCreated;

    @Id
    @Column(name = "product_id")
    private Long product;
}
