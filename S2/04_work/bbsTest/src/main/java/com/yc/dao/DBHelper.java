package com.yc.dao;



import com.google.gson.internal.bind.util.ISO8601Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
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
		DbProperties d= DbProperties.getInstance();
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
	public int doUpdata(String sql,Object...value) throws Exception {
		System.out.println("执行的SQL语句："+sql);
		int result=0;
		try(Connection con=getConnection();
			PreparedStatement pstmt=con.prepareStatement(sql)){
			setParams(pstmt, value);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//这里的异常不能直接捕捉后就不处理了,要抛到前台去
			throw e;
		}
		return result;
	}
	//聚合查询函数
	public double selectAggreation(String sql,Object...value) throws SQLException {
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
			//这里的异常不能直接捕捉后就不处理了,要抛到前台去
			e.printStackTrace();
			throw  e;
		}
		return result;
	}



	//利用反射的机制，将从数据库中的查询出的数据封装成对象，一行对应一个对象
	public <T> List<T>select(String sql,Class<T>cls,Object...value){
		List<T>list=new ArrayList<>();
		//调用下面的select（），返回List<Map<String,Object>>
		List<Map<String,Object>>listMap=select(sql,value);
		//2.循环这个List<Map>,取出每个map
		if(listMap!=null&&listMap.size()>0){
			for(Map<String,Object> map:listMap){
				//3.参照CommenServlet中的 parseRequestToT,将map转为 T
				T t= null;
				try {
					t = getT(map,cls);
					list.add(t);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return list;
	}

	//获取一个类的get方法
	private <T> T getT(Map<String, Object> map, Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
		//1.通过class字节码创建一个对象
		T obj = cls.newInstance();
		Method[] ms = cls.getMethods();
		for (Method m : ms) {
			if (m.getName().startsWith("set")) {
				//这个m是一个setXXX(),取出XXX是什么,根据XXX,再map.get(XXX)出这个值
				String fieldName = getFieldName(m);
				Object value=map.get(fieldName);
				if(value==null){
					continue;
				}
				//判断这个m方法的参数类型,然后将v进行类型转换
				String methodeParameterTypeName=m.getParameterTypes()[0].getTypeName();
				if("int".equals(methodeParameterTypeName)||"java.lang.Integer".equals(methodeParameterTypeName)){
					Integer va=Integer.parseInt(value.toString());
					//然后调用setXXX（），将值注入
					m.invoke(obj,va);
				}else if("int".equals(methodeParameterTypeName)||"java.lang.Double".equals(methodeParameterTypeName)){
					Double va=Double.parseDouble(value.toString());
					//然后调用setXXX（），将值注入
					m.invoke(obj,va);
				}else if("float".equals(methodeParameterTypeName)||"java.lang.Float".equals(methodeParameterTypeName)){
					Float va=Float.parseFloat(value.toString());
					//然后调用setXXX（），将值注入
					m.invoke(obj,va);
				}else if("long".equals(methodeParameterTypeName)||"java.lang.Long".equals(methodeParameterTypeName)){
					Long va=Long.parseLong(value.toString());
					//然后调用setXXX（），将值注入
					m.invoke(obj,va);
				}else{
					m.invoke(obj,value.toString());
				}
			}
		}
		return obj;
	}

	private String getFieldName(Method setMethod) {
		String fieldName=setMethod.getName().substring("set".length())  ;
		//将这个fieldName的首字母改成小写
		fieldName=fieldName.substring(0,1).toLowerCase()+fieldName.substring(1);
		return fieldName;
	}

	//查询：返回值为list<map<String,Object>>
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
					String cn=columnName.get(i);//取列名
					Object value1=rs.getObject(cn);
					map.put(cn, value1);//存到map中
				}
				list.add(map);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}


}
