package com.mu.vote.mapper;

import com.mu.vote.domain.TpUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author MUZUKI
* @description 针对表【tp_user(投票系统-用户表-4)】的数据库操作Mapper
* @createDate 2022-12-27 14:51:59
* @Entity generator.domain.TpUser
*/
public interface TpUserMapper extends BaseMapper<TpUser> {
    /**
     * 注册用户
     * @param uname
     * @param pwd
     */
    @Insert("insert into tp_user values(default,#{uname},#{pwd})")
    void insert(@Param("uname") String uname,@Param("pwd") String pwd);

    @Select("select * from tp_user where uname=#{name} and pwd=#{pwd}")
    TpUser selectForLogin(@Param("name") String name,@Param("pwd") String pwd);
}




