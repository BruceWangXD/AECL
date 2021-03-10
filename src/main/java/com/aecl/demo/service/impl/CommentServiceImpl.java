package com.aecl.demo.service.impl;

import com.aecl.demo.dao.CommentMapper;
import com.aecl.demo.dao.PersonInfoMapper;
import com.aecl.demo.entity.Comment;
import com.aecl.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    PersonInfoMapper personInfoMapper;

    @Override
    public void addComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    @Override
    public void deleteCommentById(int id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Comment> getAllComment() {
        List<Comment> comments = commentMapper.getAllComment();
        for (Comment comment : comments) {
            comment.setPersonName(personInfoMapper.selectUserNameById(comment.getCpersonId()));
        }
        return comments;
    }

    @Override
    public List<Comment> getAllCommentByPid(int pid) {
        List<Comment> comments = commentMapper.getAllCommentByPid(pid);
        for (Comment comment : comments) {
            comment.setPersonName(personInfoMapper.selectUserNameById(comment.getCpersonId()));
        }
        return comments;
    }

    @Override
    public void deleteCommentByUserId(Integer id) {
        commentMapper.deleteByUserId(id);
    }

    @Override
    public void deleteCommentByPostId(Integer id) {
        commentMapper.deleteByPostId(id);
    }
}
