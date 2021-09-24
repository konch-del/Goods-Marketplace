package ru.netcracker.studentsummer2021.goodsmarketplace.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.netcracker.studentsummer2021.goodsmarketplace.models.Users;

import java.util.Collection;
import java.util.List;

public class UserSecurity implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetails fromUser(Users user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                user.getAccountType().getAuthorities()
        );
    }
}
