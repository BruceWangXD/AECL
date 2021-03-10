package com.aecl.demo.dao;

import com.aecl.demo.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(int commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> getAllComment();

    List<Comment> getAllCommentByPid(int pid);

    void deleteByUserId(int userId);

    void deleteByPostId(int postId);

}