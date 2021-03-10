package com.aecl.demo.dao;

import com.aecl.demo.entity.CateExercise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CateExerciseMapper {
    int deleteByPrimaryKey(Integer categoryExerciseId);

    int insert(CateExercise record);

    int insertSelective(CateExercise record);

    CateExercise selectByPrimaryKey(Integer categoryExerciseId);

    int updateByPrimaryKeySelective(CateExercise record);

    int updateByPrimaryKey(CateExercise record);

    List<CateExercise> listAllCategory();

    String selectCategoryById(Integer id);
}