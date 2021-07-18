package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_model")
    private String model;

    @Column
    private String article;

    @Column(name = "desc_")
    private String desc;

    @Column
    private String weight;

    @Column
    private String deminsions;

    @Column(name = "date_released")
    private GregorianCalendar dateReleased;

    @Column(name = "date_creation")
    private GregorianCalendar dateCreated;

    @Column(name = "modified_date")
    private GregorianCalendar modifiedDate;

    @Column(name = "category_id")
    private Long category;

    @Column(name = "manufacturer_id")
    private Long manufacturer;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDeminsions() {
        return deminsions;
    }

    public void setDeminsions(String deminsions) {
        this.deminsions = deminsions;
    }

    public GregorianCalendar getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(GregorianCalendar dateReleased) {
        this.dateReleased = dateReleased;
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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Long manufacturer) {
        this.manufacturer = manufacturer;
    }
}
