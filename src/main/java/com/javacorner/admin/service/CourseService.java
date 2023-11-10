package com.javacorner.admin.service;

import com.javacorner.admin.entity.Course;

import java.util.List;

public interface CourseService {

    Course loadCourseById(Long courseId);

    Course createCourse(String courseName, String courseDuration, String courseDescription, Long instructorId);

    Course createOrUpdateCourse(Course course);

    List<Course> findCoursesByCourseName(String keyword);

    void assignStudentToCourse(Long courseId, Long studentId);

    List<Course> fetchAll();

    List<Course> fetchCoursesForStudent(Long studentId);

    void removeCourse(Long courseId);
}
