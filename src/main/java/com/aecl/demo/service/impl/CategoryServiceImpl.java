package com.aecl.demo.service.impl;

import com.aecl.demo.dao.CateExerciseMapper;
import com.aecl.demo.entity.CateExercise;
import com.aecl.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CateExerciseMapper cateExerciseMapper;

    @Override
    public void addCategory(CateExercise cateExercise) {
        cateExerciseMapper.insert(cateExercise);
    }

    @Override
    public void deleteCategoryById(Integer id) {
            cateExerciseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateExerciseCategory(CateExercise cateExercise) {
        cateExerciseMapper.updateByPrimaryKey(cateExercise);
    }

    @Override
    public CateExercise getOneById(Integer id) {
        return cateExerciseMapper.selectByPrimaryKey(id);

        //return null;
    }

    @Override
    public List<CateExercise> listAllCategory() {
        return cateExerciseMapper.listAllCategory();

        //return null;
    }
}

