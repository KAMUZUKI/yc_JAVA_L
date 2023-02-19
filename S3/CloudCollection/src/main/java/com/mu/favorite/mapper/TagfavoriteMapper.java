package com.mu.favorite.mapper;

import com.mu.favorite.domain.Tagfavorite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
* @author MUZUKI
* @description 针对表【tagfavorite】的数据库操作Mapper
* @createDate 2022-12-30 15:01:07
* @Entity generator.domain.Tagfavorite
*/
public interface TagfavoriteMapper {
    /**
     * 插入一条记录
     * @param tid
     * @param fid
     */
    @Insert("insert into tagfavorite values (#{tid},#{fid})")
    void insert(@Param("tid") Integer tid,@Param("fid") Integer fid);

    /**
     * 通过tname fid插入记录
     * @param tag
     * @param fid
     */
    @Insert("insert into tagfavorite SELECT\n" +
            "tid,#{fid} from tag where tname = #{tname}")
    void insertByTname(@Param("tname") String tag,@Param("fid") Integer fid);
}
