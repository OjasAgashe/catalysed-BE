package com.ojas.gcp.firstappenginetryout.auth;

import com.ojas.gcp.firstappenginetryout.entity.AppUser;
import com.ojas.gcp.firstappenginetryout.entity.enums.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class SessionUser implements UserDetails {
    //using decorative design to deligate the AppUser (Principle) methods to the user entity methods
    private AppUser user;
    private Long id;

//    private String userName;
//    private String password;
//    private boolean active;
//    private List<GrantedAuthority> authorities;


//    public AppUser(String userName) {
//        this.userName = userName;
//    }

    public SessionUser(AppUser user) {
        this.user = user;
        this.id = user.getId();
//        this.userName = user.getUserName();
//        this.password = user.getPassword();
//        this.active = user.isActive();
//        this.authorities = Arrays.stream(user.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        this.userName = userName;
    }

    public SessionUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.stream(this.user.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getFirstName();
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
        return this.user.isAccountActivated();
    }

    public UserType getUserType() {
        return user.getType();
    }

    public String getEmailId() {
        return this.user.getEmail();
    }

    public Long getId() {
        return this.user.getId();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
