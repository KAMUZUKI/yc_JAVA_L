package com.mu.movie;

import com.mu.movie.bean.SpPlan;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author : MUZUKI
 */
public interface SpPlanMapper {
    /**
     * delete by primary key
     *
     * @param id
     * @return
     */
    @Select("select * from sp_plan where id=#{id}")
    @Results(id = "rm1",
            value = {
                    @Result(column = "id", property = "id", id = true),
                    @Result(column = "play_time", property = "playTime"),
                    @Result(column = "mid", property = "movie",
                            one = @One(select = "com.mu.movie.SqMovieMapper.selectById")),
                    @Result(column = "hid", property = "hall",
                            one = @One(select = "selectHallById", fetchType = FetchType.LAZY))
            })
    SpPlan selectById(Integer id);

    /**
     * 通过价格查询
     *
     * @param price
     * @return
     */
    List<SpPlan> selectByPrice(int price);
}
