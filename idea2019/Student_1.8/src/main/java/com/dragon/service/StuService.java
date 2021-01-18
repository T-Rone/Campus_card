package com.dragon.service;

import java.util.List;

import com.dragon.entity.*;

import com.dragon.util.tag.PageModel;;


public interface StuService {


    public void insertDepartment(Department paramDepartment);

    List<Department> selectAllDepartment(Department department, PageModel pageModel);

    Department findDepartById(Integer paramInteger);

    void modifyDepart(Department department);

    void removeDepartById(int parseInt);


    void insertStudent(Student paramStudent);

    List<Student> selectAllStu(Student student, PageModel pageModel);

    Student findStuById(Integer paramInteger);

    void modifyStu(Student student);

    void removeStuById(int parseInt);

    List<Student> findAllStudent();

    void insertCourse(Course paramCourse);

    List<Course> selectAllCourse(Course course, PageModel pageModel);

    Course findCourseById(Integer paramInteger);

    void modifyCourse(Course course);

    void removeCourseById(int parseInt);

    List<Course> findAllCourse();

    void insertGrade(Grade paramGrade);

    List<Grade> selectAllGrade(Grade grade, PageModel pageModel);

    Grade findGradeById(Integer paramInteger);

    void modifyGrade(Grade grade);

    void removeGradeById(int parseInt);

    User login(String paramString1, String paramString2);

    public User findUserById(Integer paramInteger);

    public List<User> findUser(User paramUser, PageModel paramPageModel);

    public void removeUserById(Integer paramInteger);

    public void modifyUser(User paramUser);

    public void addUser(User paramUser);

}