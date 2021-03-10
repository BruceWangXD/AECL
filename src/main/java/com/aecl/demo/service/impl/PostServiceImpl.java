package com.aecl.demo.service.impl;

import com.aecl.demo.dao.CommentMapper;
import com.aecl.demo.dao.CourseMapper;
import com.aecl.demo.dao.PersonInfoMapper;
import com.aecl.demo.dao.PostMapper;
import com.aecl.demo.entity.Post;
import com.aecl.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    PersonInfoMapper personInfoMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CommentMapper commentMapper;

    @Override
    public void addPost(Post post) {
        postMapper.insertSelective(post);
    }

    @Override
    public void deletePostById(int id) {
        commentMapper.deleteByPostId(id);
        postMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> temp = postMapper.getAllPost();
        for (Post post : temp) {
            post.setPersonName(personInfoMapper.selectUserNameById(post.getPpersonId()));
        }
        return temp;
    }

    @Override
    public List<Post> getAllPostByCid(int cid) {
        List<Post> temp = postMapper.getAllPostByCid(cid);
        for (Post post : temp) {
            post.setPersonName(personInfoMapper.selectUserNameById(post.getPpersonId()));
        }
        return temp;
    }

    @Override
    public List<Post> getAllHottestPostByCid(int cid) {
        List<Post> temp = postMapper.getAllHottestPostByCid(cid);
        for (Post post : temp) {
            post.setPersonName(personInfoMapper.selectUserNameById(post.getPpersonId()));
        }
        return temp;
    }

    @Override
    public List<Post> getAllPinnedPostByCid(int cid) {
        List<Post> temp = postMapper.getAllPinnedPostByCid(cid);
        for (Post post : temp) {
            post.setPersonName(personInfoMapper.selectUserNameById(post.getPpersonId()));
        }
        return temp;

    }

    @Override
    public Post getOneByPid(int pid) {
        Post post = postMapper.selectByPrimaryKey(pid);
        post.setPersonName(personInfoMapper.selectUserNameById(post.getPpersonId()));
        return post;
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updateByPrimaryKeySelective(post);
    }

    @Override
    public Integer getCourseIdByPid(int pid) {
        return postMapper.selectCourseIdByPostId(pid);
    }

    @Override
    public List<Post> getAllPostByUid(int uid) {
        return postMapper.selectAllPostByUid(uid);
    }


}
