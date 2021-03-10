package com.aecl.demo.service.impl;

import com.aecl.demo.dao.CourseMapper;
import com.aecl.demo.entity.Course;
import com.aecl.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public void addCourse(Course course) {
            courseMapper.insert(course);
    }

    @Override
    public void deleteCourseById(int id) {
            courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();

        //return null;
    }

    @Override
    public void updateCourse(Course course) {
            courseMapper.updateByPrimaryKey(course);
    }

    @Override
    public List<Course> getHotCourses() {
        return courseMapper.getHotCourses();

        //return null;
    }


}
