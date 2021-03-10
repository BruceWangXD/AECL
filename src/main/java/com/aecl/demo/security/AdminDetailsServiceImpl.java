package com.aecl.demo.security;

import com.aecl.demo.dao.AdminMapper;
import com.aecl.demo.dao.PersonInfoMapper;
import com.aecl.demo.entity.Admin;
import com.aecl.demo.entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private final AdminMapper adminMapper;

    public AdminDetailsServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminMapper.selectByAuth(username);

        if (admin == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return User.parse(admin);
    }
}
