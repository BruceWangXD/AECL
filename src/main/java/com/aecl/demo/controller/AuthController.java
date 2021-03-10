package com.aecl.demo.controller;

import com.aecl.demo.security.User;
import com.aecl.demo.service.AdminService;
import com.aecl.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    @PostMapping(value = "/login", produces = "application/json")
    public Object login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        // This function is used to login
        // response is used to show login situations
        return userService.login(response, username, password);
    }

    @PostMapping(value = "/adminLogin", produces = "application/json")
    // This function is used for admin to login
    public Object adminLogin(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        return adminService.login(response, username, password);
    }

    @GetMapping(value = "/current", produces = "application/json")
    // Achieve the current token of the specfic user
    public Object currentUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @PostMapping(value = "/logout", produces = "application/json")
    // Simply Log out
    public Object logout(HttpServletResponse response) {
        userService.logout(response);
        return null;
    }
}
