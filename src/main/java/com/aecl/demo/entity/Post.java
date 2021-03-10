package com.aecl.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Post {
    private Integer postId;

    private Integer pcourseId;

    private Integer like;

    private Boolean isPinned;

    private Integer ppersonId;

    private String personName;

    private Date postTime;

    private String postTitle;

    private String postContent;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getPcourseId() {
        return pcourseId;
    }

    public void setPcourseId(Integer pcourseId) {
        this.pcourseId = pcourseId;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Boolean getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(Boolean isPinned) {
        this.isPinned = isPinned;
    }

    public Integer getPpersonId() {
        return ppersonId;
    }

    public void setPpersonId(Integer ppersonId) {
        this.ppersonId = ppersonId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+10")
    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent == null ? null : postContent.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}