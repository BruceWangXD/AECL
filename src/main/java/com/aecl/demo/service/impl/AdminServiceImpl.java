package com.aecl.demo.service.impl;

import com.aecl.demo.dao.AdminMapper;
import com.aecl.demo.entity.Admin;
import com.aecl.demo.entity.JsonResult;
import com.aecl.demo.entity.PersonInfo;
import com.aecl.demo.security.AdminJwtHelper;
import com.aecl.demo.security.JwtHelper;
import com.aecl.demo.security.User;
import com.aecl.demo.service.AdminService;
import com.aecl.demo.util.BCryptUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AdminJwtHelper jwtHelp;

    @Override
    public JsonResult<String> login(HttpServletResponse response, String username, String password) {
        Admin admin = adminMapper.selectByAuth(username);
        if (admin == null) {
            throw new RuntimeException("Incorrect Username or Password");
        }
        String storedHash = admin.getPassword();
        BCryptUtil bCryptUtil = new BCryptUtil();
        boolean match = bCryptUtil.matches(password, storedHash);
        if (!match) {
            throw new RuntimeException("Incorrect Username or Password");
        }
        User loginUser = User.parse(admin);
        String token = jwtHelp.generateToken(loginUser);
        return new JsonResult<String>(token);
    }

    @Override
    public String getEmailById(Integer uid) {
        return adminMapper.selectEmailById(uid);
    }
}
