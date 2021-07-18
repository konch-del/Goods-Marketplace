package ru.netcracker.studentsummer2021.goodsmarketplace.dto.users;


/**
 * Data Transfer Object класс
 * Отображает пользователя для другого пользователя
 */
public class UsersPublicDTO implements UsersDTO {

    private Long id;
    private String username;
    private String fcs;
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
