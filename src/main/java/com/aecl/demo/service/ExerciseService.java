package com.aecl.demo.service;

import com.aecl.demo.entity.CateExercise;
import com.aecl.demo.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    void addExercise(Exercise exercise);

    void deleteExerciseId(int id);

    List<Exercise> getAllExercises();

    void updateExercise(Exercise exercise);

    Exercise getOneById(int id);

    List<Exercise> getExercisesByCategoryId(int cid);

}
