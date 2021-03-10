package com.aecl.demo.entity;

public class CateExercise {
    private Integer categoryExerciseId;

    private String content;

    public Integer getCategoryExerciseId() {
        return categoryExerciseId;
    }

    public void setCategoryExerciseId(Integer categoryExerciseId) {
        this.categoryExerciseId = categoryExerciseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}