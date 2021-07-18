package ru.netcracker.studentsummer2021.goodsmarketplace.dto.users;

import ru.netcracker.studentsummer2021.goodsmarketplace.models.Role;
import java.util.GregorianCalendar;


/**
 * Data Transfer Object класс
 * Отображает пользователя для администратора
 */
public class UsersAdminDTO extends UsersPrivatDTO implements UsersDTO {

    private Role accountType;
    private String password;
    private GregorianCalendar dateOfCreation;
    private GregorianCalendar lastLoginDate;

    public Role getAccountType() {
        return accountType;
    }

    public void setAccountType(Role accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
