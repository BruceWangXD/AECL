package com.aecl.demo.service;

import com.aecl.demo.entity.Course;
import com.aecl.demo.entity.Post;

import java.util.List;

public interface PostService {
    void addPost(Post post);

    void deletePostById(int id);

    List<Post> getAllPost();

    List<Post> getAllPostByCid(int cid);

    List<Post> getAllHottestPostByCid(int cid);

    List<Post> getAllPinnedPostByCid(int cid);

    Post getOneByPid(int pid);

    void updatePost(Post post);

    Integer getCourseIdByPid(int pid);

    List<Post> getAllPostByUid(int uid);

}
