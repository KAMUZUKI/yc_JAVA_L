package com.mu02.dao;

import com.mu02.domain.Student;
import com.mu02.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StudentDaoImpl implements StudentDao{
    @Override
    public ArrayList<Student> findAll() {
        ArrayList<Student> list = new ArrayList<>();
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try{

            con = JDBCUtils.getConnection();

            //3.获取执行者对象
            stat = con.createStatement();

            //4.执行sql语句，并且接收返回的结果集
            String sql = "SELECT * FROM student";
            rs = stat.executeQuery(sql);

            //5.处理结果集
            while(rs.next()) {
                Integer sid = rs.getInt("sid");
                String sname = rs.getString("sname");
                String deptName = rs.getString("deptname");
                Integer sage = rs.getInt("sage");

                //封装Student对象
                Student stu = new Student(sid,sname,deptName,sage);

                //将student对象保存到集合中
                list.add(stu);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,stat,rs);
        }
        //将集合对象返回
        return list;
    }

    /*
        条件查询，根据id查询学生信息
     */
    @Override
    public Student findById(Integer id) {
        Student stu = new Student();
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        try{

            con = JDBCUtils.getConnection();

            //3.获取执行者对象
            stat = con.createStatement();

            //4.执行sql语句，并且接收返回的结果集
            String sql = "SELECT * FROM student WHERE sid='"+id+"'";
            rs = stat.executeQuery(sql);

            //5.处理结果集
            while(rs.next()) {
                Integer sid = rs.getInt("sid");
                String name = rs.getString("sname");
                String deptName = rs.getString("deptname");
                Integer age = rs.getInt("sage");

                //封装Student对象
                stu.setSid(sid);
                stu.setName(name);
                stu.setDeptName(deptName);
                stu.setAge(age);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,stat,rs);
        }
        //将对象返回
        return stu;
    }

    /*
        添加学生信息
     */
    @Override
    public int insert(Student stu) {
        Connection con = null;
        Statement stat = null;
        int result = 0;
        try{
            con = JDBCUtils.getConnection();

            //3.获取执行者对象
            stat = con.createStatement();

            //4.执行sql语句，并且接收返回的结果集
            String deptName = stu.getDeptName();
            String sql = "INSERT INTO student VALUES ('"+stu.getSid()+"','"+stu.getName()+"','"+deptName+"','"+stu.getAge()+"')";
            result = stat.executeUpdate(sql);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,stat);
        }
        //将结果返回
        return result;
    }

    /*
        修改学生信息
     */
    @Override
    public int update(Student stu) {
        Connection con = null;
        Statement stat = null;
        int result = 0;
        try{
            con = JDBCUtils.getConnection();

            //3.获取执行者对象
            stat = con.createStatement();

            //4.执行sql语句，并且接收返回的结果集
            String deptName = stu.getDeptName();
            String sql = "UPDATE student SET sid='"+stu.getSid()+"',sname='"+stu.getName()+"',deptName='"+deptName+"',sage='"+stu.getAge()+"' WHERE sid='"+stu.getSid()+"'";
            result = stat.executeUpdate(sql);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,stat);
        }
        //将结果返回
        return result;
    }

    /*
        删除学生信息
     */
    @Override
    public int delete(Integer id) {
        Connection con = null;
        Statement stat = null;
        int result = 0;
        try{
            con = JDBCUtils.getConnection();

            //3.获取执行者对象
            stat = con.createStatement();

            //4.执行sql语句，并且接收返回的结果集
            String sql = "DELETE FROM student WHERE sid='"+id+"'";
            result = stat.executeUpdate(sql);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,stat);
        }
        //将结果返回
        return result;
    }
}
