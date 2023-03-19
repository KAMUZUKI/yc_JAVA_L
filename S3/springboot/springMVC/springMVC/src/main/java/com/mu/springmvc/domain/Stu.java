package com.mu.springmvc.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 
 * @author MUZUKI
 * @TableName stu
 */
@Data
public class Stu{
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String uname;

    /**
     * 
     */
    private String pwd;

    /**
     * 
     */
    private String head;

    /**
     * 
     */
    private String lifes;
}