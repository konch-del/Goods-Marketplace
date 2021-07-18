package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "sales_unit")
public class SalesUnit {

    @Id
    @Column(name = "su_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int quantity;

    @Column
    private float price;

    @Column(name = "shipping_cost")
    private float shipCost;

    @Column(name = "desc_")
    private String desc;

    @Column(name = "date_created")
    private GregorianCalendar dateCreated;

    @Column(name = "modified_date")
    private GregorianCalendar modifiedDate;

    @Column(name = "shop_id")
    private Long shop;

    @Column(name = "product_id")
    private Long product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getShipCost() {
        return shipCost;
    }

    public void setShipCost(float shipCost) {
        this.shipCost = shipCost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public GregorianCalendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(GregorianCalendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public GregorianCalendar getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(GregorianCalendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getShop() {
        return shop;
    }

    public void setShop(Long shop) {
        this.shop = shop;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }
}
