package com.aecl.demo.dao;

import com.aecl.demo.entity.Course;
import com.aecl.demo.entity.Exercise;
import com.aecl.demo.entity.PersonInfo;
import com.aecl.demo.entity.Post;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonInfoMapper {
    int deleteByPrimaryKey(Integer personId);

    int insert(PersonInfo record);

    int insertSelective(PersonInfo record);

    PersonInfo selectByPrimaryKey(Integer personId);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    int updateByPrimaryKeySelective(PersonInfo record);

    int updateByPrimaryKey(PersonInfo record);

    PersonInfo selectByAuth(String username);

    Integer getcourse1(int id);

    Integer getcourse2(int id);

    Integer getcourse3(int id);

    Integer getcourse4(int id);

    List<Course> getAllUserCourses(int id);

    List<Exercise> getAllUserExercises(int id);

    List<Post> getAllUserPosts(int id);

    List<PersonInfo> getAllUsers();

    String selectUserNameById(Integer id);

    Integer selectByPidCid(Integer pid, Integer cid);

    String selectHashById(Integer id);

    void updatecoursepidcid1(int pid, int cid);

    void updatecoursepidcid2(int pid, int cid);

    void updatecoursepidcid3(int pid, int cid);

    void updatecoursepidcid4(int pid, int cid);

}