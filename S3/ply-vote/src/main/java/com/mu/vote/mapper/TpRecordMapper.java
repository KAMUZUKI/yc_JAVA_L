package com.mu.vote.mapper;

import com.mu.vote.domain.TpRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
* @author MUZUKI
* @description 针对表【tp_record(投票系统-投票记录表-6)】的数据库操作Mapper
* @createDate 2022-12-27 14:51:59
* @Entity generator.domain.TpRecord
*/
public interface TpRecordMapper extends BaseMapper<TpRecord> {
    @Override
    @Insert("insert into tp_record values(default,#{vid},#{iid},#{usid})")
    int insert(TpRecord entity);
}




