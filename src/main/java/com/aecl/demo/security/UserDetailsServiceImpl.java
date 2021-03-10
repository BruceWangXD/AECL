package com.aecl.demo.security;


import com.aecl.demo.dao.AdminMapper;
import com.aecl.demo.dao.PersonInfoMapper;
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
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final PersonInfoMapper personInfoMapper;

    public UserDetailsServiceImpl(PersonInfoMapper personInfoMapper) {
        this.personInfoMapper = personInfoMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonInfo personInfo = personInfoMapper.selectByAuth(username);

        if (personInfo == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return User.parse(personInfo);
    }
}
