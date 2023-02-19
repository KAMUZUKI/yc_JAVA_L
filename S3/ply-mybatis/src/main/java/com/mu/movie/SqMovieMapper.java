package com.mu.movie;

import com.mu.movie.bean.SpMovie;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MUZUKI
 */

public interface SqMovieMapper {
    /**
     * 根据id查询电影
     * @param id
     * @return
     */
    SpMovie selectById(int id);

    /**
     * 根据电影类型查询电影
     * @param type
     * @return
     */
    List<SpMovie> selectByType(String type);

    /**
     * 根据电影名查询电影
     * @param name
     * @return
     */
    List<SpMovie> selectLikeName(String name);

    @Select("select * from sp_movie where name=#{name}")
    List<SpMovie> selectByName(String name);
}
