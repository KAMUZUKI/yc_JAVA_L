package com.mu.vote.mapper;

import com.mu.vote.domain.TpItems;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author MUZUKI
* @description 针对表【tp_items(投票系统-题目选项表-9)】的数据库操作Mapper
* @createDate 2022-12-27 14:51:59
* @Entity generator.domain.TpItems
*/
public interface TpItemsMapper extends BaseMapper<TpItems> {
    /**
     * 插入题目
     * @param entity
     * @return
     */
    @Override
    @Insert("insert into tp_items values(default,#{iname},#{vid})")
    int insert(TpItems entity);

    /**
     * 根据vid查询
     * @param vid
     * @return
     */
    @Select("select a.*,IFNULL(b.cnt,0) `records` from tp_items a left join " +
            "(select iid,count(*) cnt  from tp_record b group by iid) b on a.id=b.iid " +
            " where a.vid=#{vid}")
    List<TpItems> selectByVid(int vid);
}




