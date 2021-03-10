package com.aecl.demo.controller;


import com.aecl.demo.security.AdminJwtHelper;
import com.aecl.demo.security.JwtHelper;
import com.aecl.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseController {

    @Autowired
    AdminService adminService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;
    @Autowired
    CourseService courseService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    FavExerciseService favExerciseService;
    @Autowired
    FavPostService favPostService;
    @Autowired
    MessageService messageService;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    JwtHelper jwtHelp;
    @Autowired
    AdminJwtHelper adminJwtHelper;

}
