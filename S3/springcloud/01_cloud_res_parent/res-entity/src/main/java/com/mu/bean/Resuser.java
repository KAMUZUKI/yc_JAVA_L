package com.mu.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: yc119res
 * @description:
 * @author: zy
 * @create: 2022-10-09 08:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resuser implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userid ;
    private String username;
    private String pwd ;
    private String  email;
}
