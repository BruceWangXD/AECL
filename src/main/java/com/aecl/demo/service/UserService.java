package com.aecl.demo.service;

import com.aecl.demo.entity.*;
import com.aecl.demo.security.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface UserService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    JsonResult login(HttpServletResponse response, String username, String password);

    void addUser(PersonInfo personInfo);

    void updateUser(PersonInfo personInfo);

    void updateUserTheme(PersonInfo personInfo);

    void updateUserCourse(PersonInfo personInfo);

    void logout(HttpServletResponse response);

    List<Course> getAllUserCourses(int id);

    List<Exercise> getAllUserExercises(int id);

    //fav posts
    List<Post> getAllUserPosts(int id);

    List<PersonInfo> getAllUsers();

    List<PersonInfo> getAllUsersFiltered();

    Integer getcourse1(int id);

    Integer getcourse2(int id);

    Integer getcourse3(int id);

    Integer getcourse4(int id);

    Boolean checkByPidCid(Integer pid, Integer cid);

    String getHashById(Integer id);

    void updateCourse(int pid, int cid);

    void deleteUserById(int id);

}
