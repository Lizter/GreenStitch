package com.example.GreenStitch_Assignment.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implement as needed
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Other overridden methods

    @Override
    public boolean isAccountNonExpired() {
        // Implement as needed
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implement as needed
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implement as needed
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Implement as needed
        return true;
    }

    public long getId() {
        return id;
    }
}

