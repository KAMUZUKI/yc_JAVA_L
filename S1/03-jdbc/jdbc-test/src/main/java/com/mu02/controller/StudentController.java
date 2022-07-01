package com.mu02.controller;
import com.mu02.domain.Student;
import com.mu02.service.StudentService;
import com.mu02.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {
    private StudentService service = new StudentServiceImpl();

    Scanner sc = new Scanner(System.in);

    public void findAll() {
        ArrayList<Student> list = service.findAll();
        for(Student stu : list) {
            System.out.println(stu);
        }
    }

    /*
        条件查询，根据id查询学生信息
     */
    public void findById() {
        System.out.println("请输入要查询的学生学号：");
        int sid = sc.nextInt();
        Student stu = service.findById(sid);
        System.out.println(stu);
    }

    /*
        添加学生信息
     */
    public void insert() {

        System.out.println("请输入学生学号：");
        int sid = sc.nextInt();
        sc.nextLine();
        System.out.println("请输入学生姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生部门：");
        String deptName = sc.nextLine();
        System.out.println("请输入学生年龄：");
        int age = sc.nextInt();

        Student stu = new Student(sid,name,deptName,age);
        int result = service.insert(stu);

        if(result!=0) {
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
    /*
        批量添加学生信息
    */
    public void batchInsert() {
        service.batchInsert();
    }

    /*
        修改学生信息
     */
    public void update() {
        System.out.println("请输入要修改的学生学号：");
        int modify = sc.nextInt();
        Student stu = service.findById(modify);

        sc.nextLine();
        System.out.println("请输入学生姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生部门：");
        String deptName = sc.nextLine();
        System.out.println("请输入学生年龄：");
        int age = sc.nextInt();
        stu.setName(name);
        stu.setDeptName(deptName);
        stu.setAge(age);
        int result = service.update(stu);

        if(result != 0) {
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    /*
        删除学生信息
     */
    public void delete(){
        System.out.println("请输入要删除的学生学号：");
        int sid = sc.nextInt();

        int result = service.delete(sid);
        if(result != 0) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
}
