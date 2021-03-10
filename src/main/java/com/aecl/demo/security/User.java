package com.aecl.demo.security;


import com.aecl.demo.entity.Admin;
import com.aecl.demo.entity.PersonInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;


@Scope("session")
public class User implements UserDetails {

    private final String username;
    private final int id;
    private final Collection<? extends GrantedAuthority> authorities;

    public User(String username, int id, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    public int getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
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

    public boolean isAdmin() {
        boolean flag = false;
        for (GrantedAuthority authority : this.authorities) {
            if ("ADMIN".equals(authority.getAuthority())) {
                flag = true;
            }
        }
        return flag;
    }

    public static User parse(PersonInfo personInfo) {
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("NORMAL"));
        return new User(personInfo.getEmail(), personInfo.getPersonId(), grantedAuthorities);
    }

    public static User parse(Admin admin) {
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        return new User(admin.getEmail(), admin.getAdminId(), grantedAuthorities);
    }

}
