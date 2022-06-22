package com.mu02.dao;

import com.mu02.domain.Student;

import java.util.ArrayList;

public interface StudentDao {
    //查询所有学生信息
    public abstract ArrayList<Student> findAll();

    //条件查询，根据id获取学生信息
    public abstract Student findById(Integer id);

    //新增学生信息
    public abstract int insert(Student stu);

    //修改学生信息
    public abstract int update(Student stu);

    //删除学生信息
    public abstract int delete(Integer id);
}
