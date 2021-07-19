package ru.netcracker.studentsummer2021.goodsmarketplace.dto.users;


/**
 * Data Transfer Object класс
 * Отображает пользователя для самого себя
 */
public class UsersPrivatDTO extends UsersPublicDTO implements UsersDTO {

    private String email;
    private String address;
    private String phoneNumber;
    private Long shopId;
    private boolean activation;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
