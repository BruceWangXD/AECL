package com.aecl.demo.service;

import com.aecl.demo.entity.Course;

import java.util.List;

public interface CourseService {
    void addCourse(Course course);

    void deleteCourseById(int id);

    List<Course> getAllCourse();


    void updateCourse(Course course);

    List<Course> getHotCourses();

}
