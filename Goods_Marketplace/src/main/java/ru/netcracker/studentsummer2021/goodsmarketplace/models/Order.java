package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "order_")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "delivery_mark")
    private int mark;

    @Column(name = "delivery_date")
    private GregorianCalendar deliveryDate;

    @Column
    private String address;

    @Column
    private String comm;

    @Column(name = "order_status")
    private String status;

    @Column(name = "date_created")
    private GregorianCalendar dateCreated;

    @Column(name = "modified_date")
    private GregorianCalendar modifiedDate;

    @Column(name = "modified_date_os")
    private GregorianCalendar modDateOS;

    @Column(name = "user_id")
    private Long user;

    @Column(name = "su_id")
    private Long su;

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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public GregorianCalendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(GregorianCalendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public GregorianCalendar getModDateOS() {
        return modDateOS;
    }

    public void setModDateOS(GregorianCalendar modDateOS) {
        this.modDateOS = modDateOS;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getSu() {
        return su;
    }

    public void setSu(Long su) {
        this.su = su;
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
