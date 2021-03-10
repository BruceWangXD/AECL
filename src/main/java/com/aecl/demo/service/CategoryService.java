package com.aecl.demo.service;

import com.aecl.demo.entity.CateExercise;

import java.util.List;

public interface CategoryService {
    void addCategory(CateExercise cateExercise);

    void deleteCategoryById(Integer id);

    void updateExerciseCategory(CateExercise cateExercise);

    CateExercise getOneById(Integer id);

    List<CateExercise> listAllCategory();
}
