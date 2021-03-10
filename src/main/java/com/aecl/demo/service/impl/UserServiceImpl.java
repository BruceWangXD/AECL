package com.aecl.demo.service.impl;

import com.aecl.demo.dao.CourseMapper;
import com.aecl.demo.dao.ExerciseMapper;
import com.aecl.demo.dao.PersonInfoMapper;
import com.aecl.demo.dao.PostMapper;
import com.aecl.demo.entity.*;
import com.aecl.demo.security.User;
import com.aecl.demo.security.JwtHelper;
import com.aecl.demo.service.UserService;
import com.aecl.demo.util.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PersonInfoMapper personInfoMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    ExerciseMapper exerciseMapper;

    @Autowired
    PostMapper postMapper;

    @Autowired
    JwtHelper jwtHelp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personInfoMapper.loadUserByUsername(username);
    }

    @Override
    public void addUser(PersonInfo personInfo) {
        BCryptUtil util = new BCryptUtil();
        String hash = util.encode(personInfo.getPassword());
        personInfo.setPassword(hash);
        personInfoMapper.insertSelective(personInfo);
    }


    public Integer getcourse1(int id) {
        return personInfoMapper.getcourse1(id);
    }

    public Integer getcourse2(int id) {
        return personInfoMapper.getcourse2(id);
    }

    public Integer getcourse3(int id) {
        return personInfoMapper.getcourse3(id);
    }

    public Integer getcourse4(int id) {
        return personInfoMapper.getcourse4(id);
    }


    public List<Course> getAllUserCourses(int id) {
        PersonInfo fav = personInfoMapper.selectByPrimaryKey(id);
        List<Course> re = new ArrayList<>();
        Course ex1 = courseMapper.selectByPrimaryKey(fav.getPcourse1());
        if (ex1 != null) {
            re.add(ex1);
        }
        Course ex2 = courseMapper.selectByPrimaryKey(fav.getPcourse2());
        if (ex2 != null) {
            re.add(ex2);
        }
        Course ex3 = courseMapper.selectByPrimaryKey(fav.getPcourse3());
        if (ex3 != null) {
            re.add(ex3);
        }
        Course ex4 = courseMapper.selectByPrimaryKey(fav.getPcourse4());
        if (ex4 != null) {
            re.add(ex4);
        }
        return re;
    }


    //return null;
    public List<Exercise> getAllUserExercises(int id) {
        PersonInfo fav = personInfoMapper.selectByPrimaryKey(id);
        List<Exercise> re = new ArrayList<>();
        Exercise ex1 = exerciseMapper.selectByPrimaryKey(fav.getPersonId());
        if (ex1 != null) {
            re.add(ex1);
        }
        return re;
    }

    public List<Post> getAllUserPosts(int id) {
        PersonInfo fav = personInfoMapper.selectByPrimaryKey(id);
        List<Post> re = new ArrayList<>();
        Post ex1 = postMapper.selectByPrimaryKey(fav.getPersonId());
        if (ex1 != null) {
            re.add(ex1);
        }
        return re;


    }


    public List<PersonInfo> getAllUsers() {
        return personInfoMapper.getAllUsers();
    }


    @Override
    public void updateUser(PersonInfo personInfo) {
        personInfoMapper.updateByPrimaryKeySelective(personInfo);
    }

    @Override
    public void updateUserTheme(PersonInfo personInfo) {
        personInfoMapper.updateByPrimaryKeySelective(personInfo);
    }

    @Override
    public void updateUserCourse(PersonInfo personInfo) {
        personInfoMapper.updateByPrimaryKeySelective(personInfo);
    }

    @Override
    public JsonResult<String> login(HttpServletResponse response, String username, String password) {
        PersonInfo personInfo = personInfoMapper.selectByAuth(username);
        if (personInfo == null) {
            throw new RuntimeException("Incorrect Username or Password");
        }
        String storedHash = personInfo.getPassword();
        BCryptUtil bCryptUtil = new BCryptUtil();
        boolean match = bCryptUtil.matches(password, storedHash);
        if (!match) {
            throw new RuntimeException("Incorrect Username or Password");
        }
        User loginUser = User.parse(personInfo);
        String token = jwtHelp.generateToken(loginUser);
        return new JsonResult<String>(token);
    }

    @Override
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


    public Boolean checkByPidCid(Integer pid, Integer cid) {
        Integer temp = personInfoMapper.selectByPidCid(pid, cid);
        return temp != null;

    }

    @Override
    public List<PersonInfo> getAllUsersFiltered() {
        List<PersonInfo> persons = getAllUsers();
        for (PersonInfo person : persons) {
            person.setPassword(null);
            person.setEmail(null);
            person.setPcourse1(null);
            person.setPcourse2(null);
            person.setPcourse3(null);
            person.setPcourse4(null);
            person.setAcademicYear(null);
            person.setTheme(null);
        }
        return persons;
    }

    @Override
    public String getHashById(Integer id) {
        return personInfoMapper.selectHashById(id);
    }

    public void updateCourse(int pid, int cid) {
        personInfoMapper.updatecoursepidcid1(pid, cid);
        personInfoMapper.updatecoursepidcid2(pid, cid);
        personInfoMapper.updatecoursepidcid3(pid, cid);
        personInfoMapper.updatecoursepidcid4(pid, cid);
    }

    @Override
    public void deleteUserById(int id) {
        personInfoMapper.deleteByPrimaryKey(id);
    }
}
