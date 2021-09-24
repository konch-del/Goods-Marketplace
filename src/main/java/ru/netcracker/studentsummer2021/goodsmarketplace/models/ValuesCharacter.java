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

    @Column(name = "charact_id")
    private Long charact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getCharact() {
        return charact;
    }

    public void setCharact(Long charact) {
        this.charact = charact;
    }
}
