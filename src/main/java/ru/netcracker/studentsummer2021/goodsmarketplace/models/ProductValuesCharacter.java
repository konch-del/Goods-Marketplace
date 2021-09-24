package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_values_character")
public class ProductValuesCharacter {

    @Id
    @Column(name = "pvc_id")
    private Long id;

    @Column(name = "product_id")
    private Long product;

    @Column(name = "vc_id")
    private Long value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
