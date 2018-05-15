package com.kindercinema.kinderaftertindercinema.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private byte role;
    @OneToOne(mappedBy = "user")
    private KinderUser kinderUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "";
        switch (getRole()) {
            case 1:
                role = "CLIENT";
                break;
            case 2:
                role = "CASHIER";
                break;
            case 3:
                role = "MANAGER";
                break;
        }
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return getEmail();
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
}
