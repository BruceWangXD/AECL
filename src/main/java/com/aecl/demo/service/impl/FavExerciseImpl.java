package com.aecl.demo.service.impl;

import com.aecl.demo.dao.ExerciseMapper;
import com.aecl.demo.dao.FavExerciseMapper;
import com.aecl.demo.entity.Exercise;
import com.aecl.demo.entity.FavExercise;
import com.aecl.demo.service.FavExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavExerciseImpl implements FavExerciseService {

    @Autowired
    FavExerciseMapper favExerciseMapper;

    @Autowired
    ExerciseMapper exerciseMapper;

    @Override
    public void addFavExercise(FavExercise favExercise) {
        favExerciseMapper.insert(favExercise);
    }

    @Override
    public void deleteFavExerciseById(Integer id) {
        favExerciseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<FavExercise> selectFavByPID(Integer pid) {
        return favExerciseMapper.selectFavByPID(pid);
    }


    public List<Exercise> getAllFavExercise(Integer pid) {
        List<FavExercise> temp = favExerciseMapper.selectFavByPID(pid);
        List<Exercise> re = new ArrayList<>();
        for (FavExercise fav : temp) {
            Exercise ex = exerciseMapper.selectByPrimaryKey(fav.getFexerciseId());
            re.add(ex);
        }
        return re;
    }

    @Override
    public Integer getFavExerciseByUidPid(Integer uid, Integer pid) {
        return favExerciseMapper.selectFavByUidPid(uid, pid);
    }

    @Override
    public void deleteFavExerciseByUid(Integer uid) {
        favExerciseMapper.deleteFavByUid(uid);
    }

    @Override
    public void deleteFavExerciseByEid(Integer eid) {
        favExerciseMapper.deleteFavByEid(eid);
    }
}
