package com.mu02.dao;

import com.mu02.domain.Student;
import com.mu02.utils.JDBCUtils;
import com.mu02.utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
    // JDBC 批处理
preparedStatement = connection.prepareStatement(sql);
long start = System.currentTimeMillis();//开始时间
for (int i = 0; i < 5000; i++) {
    preparedStatement.setString(1,"jack"+i);
    preparedStatement.setString(2,"123");
    //将sql语句加入到批量处理包中
    preparedStatement.addBatch();
    //如果有 1000 条记录时，在执行批量操作
    if ((i+1)%1000 == 0){//因为i时从0 开始，所以当 i=999 记录时有1000条数据了
        preparedStatement.executeBatch();
        //一把清空
        preparedStatement.clearBatch();
    }
}
long end = System.currentTimeMillis();//结束 时间
System.out.println("批量处理时间 " + (end-start));
*/

public class StudentDaoImpl implements StudentDao{
    @Override
    public ArrayList<Student> findAll() {
        ArrayList<Student> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try{

            con = JDBCUtils.getConnection();

            //3.执行sql语句，并且接收返回的结果集
            String sql = "SELECT * FROM student";
            //4.获取执行者对象
            pstat = con.prepareStatement(sql);

            rs = pstat.executeQuery();

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
            JDBCUtils.close(con,pstat,rs);
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
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try{

            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM student WHERE sid=?";

            //3.获取执行者对象
            pstat = con.prepareStatement(sql);

            pstat.setInt(1,id);

            //4.执行sql语句，并且接收返回的结果集
            rs = pstat.executeQuery();

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
            JDBCUtils.close(con,pstat,rs);
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
        PreparedStatement pstat = null;
        int result = 0;
        try{
            con = JDBCUtils.getConnection();

            String deptName = stu.getDeptName();
            String sql = "INSERT INTO student VALUES (?,?,?,?)";

            //3.获取执行者对象
            pstat = con.prepareStatement(sql);

            pstat.setInt(1,stu.getSid());
            pstat.setString(2,stu.getName());
            pstat.setString(3,stu.getDeptName());
            pstat.setInt(4,stu.getAge());

            //4.执行sql语句，并且接收返回的结果集
            result = pstat.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,pstat);
        }
        //将结果返回
        return result;
    }

    /*
        批量添加学生信息
     */
    @Override
    public void batchInsert() {
        Connection con = null;
        PreparedStatement pstat = null;
        List<String> list = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\Gitresp\\yc_JAVA_L\\S1\\03-jdbc\\jdbc-test\\src\\test\\java\\com\\mu2\\a.txt"))))) {
            String s;
            while ((s = reader.readLine()) != null) {
                list.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println(list.size());

        try{
            con = JDBCUtils.getConnection();

            String sql = "INSERT INTO student VALUES (?,?,?,?)";
            String s;
            String[] ss;
            int total = 0;
            //3.获取执行者对象
            pstat = con.prepareStatement(sql);

            long start = System.currentTimeMillis();//开始时间
            for (int i = 0; i < list.size(); i++) {
                s = list.get(i);
                ss = s.split("\t");
                pstat.setInt(1,Integer.parseInt(ss[0]));
                pstat.setString(2,ss[1]);
                pstat.setString(3,"test");
                pstat.setInt(4,Integer.parseInt(ss[2]));
                //TODO:将sql语句加入到批量处理包中
                pstat.addBatch();
                //如果有 1000 条记录时，在执行批量操作
                if ((i+1)%1000 == 0){//因为i时从0 开始，所以当 i=999 记录时有1000条数据了
                    int[] result = pstat.executeBatch();
                    total += Utils.sum(result);
                    //一把清空
                    con.commit();
                    pstat.clearBatch();
                }
            }

            long end = System.currentTimeMillis();//结束 时间
            System.out.println("批量处理时间 " + (end-start));

            //4.执行sql语句，并且接收返回的结果集
            int[] result = pstat.executeBatch();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,pstat);
        }
    }
    /*
        修改学生信息
     */
    @Override
    public int update(Student stu) {
        Connection con = null;
        PreparedStatement pstat = null;
        int result = 0;
        try{
            con = JDBCUtils.getConnection();

            String deptName = stu.getDeptName();
            String sql = "UPDATE student SET sid=?,sname=?,deptName=?,sage=? WHERE sid= ?";

            //3.获取执行者对象
            pstat = con.prepareStatement(sql);

            pstat.setInt(1,stu.getSid());
            pstat.setString(2,stu.getName());
            pstat.setString(3,stu.getDeptName());
            pstat.setInt(4,stu.getAge());
            pstat.setInt(5,stu.getSid());

//            String sql = "UPDATE student SET sid='"+stu.getSid()+"',sname='"+stu.getName()+"',deptName='"+deptName+"',sage='"+stu.getAge()+"' WHERE sid='"+stu.getSid()+"'";

            //4.执行sql语句，并且接收返回的结果集
            result = pstat.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,pstat);
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
        PreparedStatement pstat = null;
        int result = 0;
        try{
            con = JDBCUtils.getConnection();

            String sql = "DELETE FROM student WHERE sid=?";

            //3.获取执行者对象
            pstat = con.prepareStatement(sql);

            pstat.setInt(1,id);

            //4.执行sql语句，并且接收返回的结果集
            result = pstat.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,pstat);
        }
        //将结果返回
        return result;
    }
}
