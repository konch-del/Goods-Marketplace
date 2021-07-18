package ru.netcracker.studentsummer2021.goodsmarketplace.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "users_")
public class Users {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_type")
    private Role accountType;

    @Column
    private String username;

    @Column(name = "pass")
    private String password;

    @Column
    private String fcs;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_creation")
    private GregorianCalendar dateOfCreation;

    @Column(name = "last_login_date")
    private GregorianCalendar lastLoginDate;

    @Column
    private boolean activation;

    @Column(name = "shop_id")
    private Long shopId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getAccountType() {
        return accountType;
    }

    public void setAccountType(Role accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcs() {
        return fcs;
    }

    public void setFcs(String fcs) {
        this.fcs = fcs;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public GregorianCalendar getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(GregorianCalendar dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public GregorianCalendar getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(GregorianCalendar lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
