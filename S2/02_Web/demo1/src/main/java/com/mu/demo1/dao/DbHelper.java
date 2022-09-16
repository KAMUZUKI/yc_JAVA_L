package com.mu.demo1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MUZUKI
 * @createdDate
 */
public class DbHelper {
	static {
		try {
			System.out.println("加载驱动");
			Class.forName(DbProperties.getInstance().getProperty("driverClassName"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Connection getConnection() {//将数据库的获取封装成一个方法
		DbProperties d=DbProperties.getInstance();
		Connection con=null;
		try {
			con=DriverManager.getConnection(d.getProperty("url"),d.getProperty("username"),d.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	//设置参数
	public void setParams(PreparedStatement pstmt,Object...value) throws SQLException {
		if(value!=null&&value.length>0) {
			for(int i=0;i<value.length;i++) {
				pstmt.setObject(i+1, value[i]);
			}
		}
	}
	//更新操作,增删改统称更新操作
	public int doUpdata(String sql,Object...value) {
		System.out.println("执行的SQL语句："+sql);
		int result=0;
		try(Connection con=getConnection();
			PreparedStatement pstmt=con.prepareStatement(sql)){
			setParams(pstmt, value);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	//聚合查询函数
	public double selectAggreation(String sql,Object...value) {
		double result=0;
		try(Connection con=getConnection();
			PreparedStatement pstmt=con.prepareStatement(sql)){
			setParams(pstmt, value);
			ResultSet r=pstmt.executeQuery();
			if(r.next()) {
				result=r.getDouble(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	/**
	 * 查询：返回值为list<map<String,Object>>
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> select(String sql,Object...value){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		try(Connection con=getConnection();
			PreparedStatement pstmt=con.prepareStatement(sql)){
			setParams(pstmt,value);
			ResultSet rs=pstmt.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int cc=rsmd.getColumnCount();
			List<String> columnName=new ArrayList<String>();
			for(int i=0;i<cc;i++) {
				columnName.add(rsmd.getColumnName(i+1));
			}
			while(rs.next()){
				Map<String, Object> map=new HashMap<String,Object>();
				for(int i=0;i<columnName.size();i++) {
					//取列名
					String cn=columnName.get(i);
					Object value1=rs.getObject(cn);
					//存到map中
					map.put(cn, value1);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


}
