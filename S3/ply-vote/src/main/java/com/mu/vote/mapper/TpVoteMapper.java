package com.mu.vote.mapper;

import com.mu.vote.domain.TpVote;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.io.Serializable;
import java.util.List;

/**
* @author MUZUKI
* @description 针对表【tp_vote(投票系统-题目表-2)】的数据库操作Mapper
* @createDate 2022-12-27 14:51:59
* @Entity generator.domain.TpVote
*/
public interface TpVoteMapper extends BaseMapper<TpVote> {
    /**
     * 插入
     * @param entity
     * @return
     */
    @Override
    @Insert("insert into tp_vote values(default,#{vname},#{vtype},#{startDate},#{endDate})")
    @Options(keyProperty = "id", keyColumn = "id",useGeneratedKeys = true)
    int insert(TpVote entity);

    /**
     * 查询全部
     * @return
     */
    @Select("select a.*,cnt `userNums` from tp_vote a left join " +
            "(select count(*) cnt,vid from (select vid,usid,count(*) from tp_record b group by usid,vid) a group by vid) b " +
            " on a.id=b.vid")
    @Results(id="rm1",value = {
            @Result(column = "id",property = "id",id=true),
            @Result(column = "id",property = "items",many = @Many(
                    select = "com.mu.vote.mapper.TpItemsMapper.selectByVid",
                    fetchType = FetchType.EAGER
            ))
    })
    List<TpVote> selectAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    @Select("select a.*,cnt `userNums` from tp_vote a left join " +
            "(select count(*) cnt,vid from (select vid,usid,count(*) from tp_record b group by usid,vid) a group by vid) b " +
            " on a.id=b.vid where a.id=#{id}")
    @ResultMap("rm1")
    TpVote selectById(Serializable id);
}




