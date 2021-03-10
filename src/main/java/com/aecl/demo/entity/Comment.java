package com.aecl.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private Integer cpostId;

    private Integer cpersonId;

    private String personName;

    private Date commentTime;

    private String content;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCpostId() {
        return cpostId;
    }

    public void setCpostId(Integer cpostId) {
        this.cpostId = cpostId;
    }

    public Integer getCpersonId() {
        return cpersonId;
    }

    public void setCpersonId(Integer cpersonId) {
        this.cpersonId = cpersonId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+10")
    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}