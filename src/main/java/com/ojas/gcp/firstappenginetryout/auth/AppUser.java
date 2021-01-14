package com.ojas.gcp.firstappenginetryout.auth;

import com.ojas.gcp.firstappenginetryout.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class AppUser implements UserDetails {
    //using decorative design to deligate the AppUser (Principle) methods to the user entity methods
    private User user;

//    private String userName;
//    private String password;
//    private boolean active;
//    private List<GrantedAuthority> authorities;


//    public AppUser(String userName) {
//        this.userName = userName;
//    }

    public AppUser(User user) {
        this.user = user;
//        this.userName = user.getUserName();
//        this.password = user.getPassword();
//        this.active = user.isActive();
//        this.authorities = Arrays.stream(user.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        this.userName = userName;
    }

    public AppUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
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
        return this.user.isActive();
    }
}
