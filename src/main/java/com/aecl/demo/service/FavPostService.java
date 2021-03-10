package com.aecl.demo.service;

import com.aecl.demo.entity.FavPost;

import java.util.List;

public interface FavPostService {
    void addFavPost(FavPost favPost);

    void deleteFavPostById(int id);

    List<FavPost> getAllFavPost();

}
