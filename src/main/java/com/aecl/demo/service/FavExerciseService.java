package com.aecl.demo.service;

import com.aecl.demo.entity.Exercise;
import com.aecl.demo.entity.FavExercise;

import java.util.List;

public interface FavExerciseService {
    void addFavExercise(FavExercise favExercise);

    void deleteFavExerciseById(Integer id);

    List<Exercise> getAllFavExercise(Integer id);

    List<FavExercise> selectFavByPID(Integer pid);

    Integer getFavExerciseByUidPid(Integer uid, Integer pid);

    void deleteFavExerciseByUid(Integer uid);

    void deleteFavExerciseByEid(Integer uid);

}
