package com.mu.movie;

import com.mu.movie.bean.SpHall;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author MUZUKI
 */
public interface SpHallMapper {
    /**
     * 根据id查询影厅
     * @param name
     * @param id
     * @return
     */
    SpHall selectBySpHall(@Param("name") String name,@Param("id") Integer id);

    /**
     * 插入影厅
     * @param hall
     * @return
     */
    @Insert("insert into sp_hall values(default,#{name},#{is3d},#{size},#{seats})")
    @Options(keyColumn = "id",keyProperty = "id",useGeneratedKeys = true)
    int insert(SpHall hall);

    /**
     * 更新影厅
     * @param hall
     */
    @Update("update sp_hall set name=#{name},is3d=#{is3d},size=#{size},seats=#{seats} where id=#{id}")
    void updateById(SpHall hall);
}
