package com.aecl.demo.service.impl;

import com.aecl.demo.dao.FavPostMapper;
import com.aecl.demo.entity.FavPost;
import com.aecl.demo.service.FavPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavPostImpl implements FavPostService {
    @Autowired
    FavPostMapper favPostMapper;

    @Override
    public void addFavPost(FavPost favPost) {
        favPostMapper.insert(favPost);
    }

    @Override
    public void deleteFavPostById(int id) {
        favPostMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<FavPost> getAllFavPost() {
        return favPostMapper.getAllFavPost();
        //return null;
    }
}
