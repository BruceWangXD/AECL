package com.aecl.demo.service.impl;

import com.aecl.demo.dao.ExerciseMapper;
import com.aecl.demo.entity.CateExercise;
import com.aecl.demo.entity.Exercise;
import com.aecl.demo.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    ExerciseMapper exerciseMapper;

    @Override
    public void addExercise(Exercise exercise) {
        exerciseMapper.insert(exercise);
    }

    @Override
    public void deleteExerciseId(int id) {
        exerciseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseMapper.selectAllExercises();
    }

    @Override
    public void updateExercise(Exercise exercise) {
        exerciseMapper.updateByPrimaryKey(exercise);
    }

    @Override
    public Exercise getOneById(int id) {
        return exerciseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Exercise> getExercisesByCategoryId(int cid) {
       return  exerciseMapper.getExercisesByCategoryId(cid);
    }


}