package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;

@Entity
@Table
public class Manufacturer {

    @Id
    @Column(name = "manufacturer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "manufacturer_name")
    private String name;

    @Column(name = "desc_")
    private String desc;

    @Column(name = "id_picture")
    private String picture;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
