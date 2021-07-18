package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import lombok.Data;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table
public class Shop {

    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "shop_name")
    private String name;

    @Column(name = "desc_")
    private String desc;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "work_time")
    private String workTime;

    @Column
    private String address;

    @Column(name = "date_create")
    private GregorianCalendar dateCreated;

    @Column(name = "modified_date")
    private GregorianCalendar modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
