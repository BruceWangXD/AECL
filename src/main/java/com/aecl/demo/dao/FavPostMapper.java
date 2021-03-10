package com.aecl.demo.dao;

import com.aecl.demo.entity.FavPost;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavPostMapper {
    int deleteByPrimaryKey(Integer favouriteId);

    int insert(FavPost record);

    int insertSelective(FavPost record);

    List<FavPost> getAllFavPost();

    FavPost selectByPrimaryKey(Integer favouriteId);

    int updateByPrimaryKeySelective(FavPost record);

    int updateByPrimaryKey(FavPost record);
}