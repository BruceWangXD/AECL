package com.aecl.demo.dao;

import com.aecl.demo.entity.Exercise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExerciseMapper {
    int deleteByPrimaryKey(Integer exerciseId);

    int insert(Exercise record);

    int insertSelective(Exercise record);

    List<Exercise> selectAllExercises();

    Exercise selectByPrimaryKey(Integer exerciseId);

    int updateByPrimaryKeySelective(Exercise record);

    int updateByPrimaryKey(Exercise record);
    List<Exercise> getExercisesByCategoryId(int cid);
}