package com.aecl.demo.dao;

import com.aecl.demo.entity.Exercise;
import com.aecl.demo.entity.FavExercise;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavExerciseMapper {
    int deleteByPrimaryKey(Integer favouriteId);

    int insert(FavExercise record);

    int insertSelective(FavExercise record);

    FavExercise selectByPrimaryKey(Integer favouriteId);

    int updateByPrimaryKeySelective(FavExercise record);

    int updateByPrimaryKey(FavExercise record);

    List<Exercise> selectExercisesByPID(Integer pid);

    List<FavExercise> selectFavByPID(Integer pid);

    Integer selectFavByUidPid(Integer uid, Integer pid);

    void deleteFavByUid(Integer uid);

    void deleteFavByEid(Integer eid);
}
