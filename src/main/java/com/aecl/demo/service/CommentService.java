package com.aecl.demo.service;


import com.aecl.demo.entity.Comment;

import java.util.List;

/**
 * Comment Service
 */
public interface CommentService {
    void addComment(Comment comment);

    void deleteCommentByUserId(Integer id);

    void deleteCommentByPostId(Integer id);

    void deleteCommentById(int id);

    List<Comment> getAllComment();

    List<Comment> getAllCommentByPid(int pid);

}
