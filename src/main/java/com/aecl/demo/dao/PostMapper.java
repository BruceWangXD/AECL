package com.aecl.demo.dao;

import com.aecl.demo.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {
    int deleteByPrimaryKey(Integer postId);

    int insert(Post record);

    int insertSelective(Post record);

    List<Post> getAllPost();

    List<Post> getAllPostByCid(int cid);
    List<Post> getAllHottestPostByCid(int cid);

    List<Post> getAllPinnedPostByCid(int cid);

    Post selectByPrimaryKey(Integer postId);

    Post getOneByPid(int pid);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    int selectCourseIdByPostId(int pid);

    List<Post> selectAllPostByUid(int uid);
}