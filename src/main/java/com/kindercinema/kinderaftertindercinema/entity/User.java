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

    public final static byte ROLE_CLIENT = 1;
    public final static byte ROLE_CASHIER = 2;
    public final static byte ROLE_MANAGER = 3;

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
            case ROLE_CLIENT:
                role = "CLIENT";
                break;
            case ROLE_CASHIER:
                role = "CASHIER";
                break;
            case ROLE_MANAGER:
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
