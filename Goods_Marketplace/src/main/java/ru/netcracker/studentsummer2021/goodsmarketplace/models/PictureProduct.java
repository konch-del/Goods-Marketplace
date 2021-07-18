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

    @Column(name = "product_id")
    private Long product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPicture() {
        return picture;
    }

    public void setPicture(Long picture) {
        this.picture = picture;
    }

    public GregorianCalendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(GregorianCalendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }
}
