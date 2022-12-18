package com.mu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-12-10 21:03
 **/

public class MybatisPlusSession {
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
        user.setSn(100);
        user.setName("张三");
        insert(user);
        user.setPwd("123456");
        user.setEmail("123456@qq.com");
        insert(user);
        updateById(user);
    }

    public static void insert(Object entity) throws IllegalAccessException {
        String tablename = entity.getClass().getSimpleName();

        String cols = "";
        String vals = "";
        List<Object> values = new ArrayList<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            final Object value = field.get(entity);
            if (value != null) {
                cols += field.getName() + ",";
                vals += "?,";
                values.add(value);
            }
        }
        String sql = "insert into " + tablename + "(" + cols.substring(0, cols.length() - 1) + ") values (" + vals.substring(0, vals.length() - 1) + ")";
        System.out.println("sql = " + sql);
        System.out.println("values = " + values);
    }

    public static void updateById(Object entity) throws IllegalAccessException {
        String tablename = entity.getClass().getSimpleName();
        TableName tableName = entity.getClass().getAnnotation(TableName.class);

        if (tableName != null) {
            tablename = tableName.value();
        }
        String cols = "";
        List<Object> values = new ArrayList<>();
        Field fieldId = null;
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            final Object value = field.get(entity);
            if (value != null) {
                final TableId tableId = field.getAnnotation(TableId.class);
                if (tableId != null) {
                    fieldId = field;
                }
                final TableField tableField = field.getAnnotation(TableField.class);
                if (tableField == null) {
                    cols += field.getName() + "=?,";
                    values.add(value);
                } else if(tableField.exist() == false){
                    continue;
                } else if(tableField.value() != null){
                    cols += tableField.value() + "=?,";
                }
                values.add(field.get(entity));
            }
        }
        cols = cols.substring(0, cols.length() - 1);

        String where = fieldId.getName() + "=?";
        values.add(fieldId.get(entity));
        String sql = "upadate %s set %s where %s";

        sql = String.format(sql, tablename, cols, where);

        System.out.println("sql = " + sql);
        System.out.println("values = " + values);

    }
}

@Data
@TableName("sys_user")
class User{
    @TableId
    private Integer sn;
    private String name;
    @TableField("password")
    private String pwd;
    private String email;

    /**
     * 不是数据库字段
     */
    @TableField(exist=false)
    private List<Object> objects;
}