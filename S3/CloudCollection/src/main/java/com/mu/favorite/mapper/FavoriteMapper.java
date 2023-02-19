package com.mu.favorite.mapper;

import com.mu.favorite.domain.Favorite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author MUZUKI
* @description 针对表【favorite】的数据库操作Mapper
* @createDate 2022-12-30 15:01:07
* @Entity generator.domain.Favorite
*/
public interface FavoriteMapper {
    /**
     * 插入一条记录
     * @param favorite
     * @return
     */
    @Insert("insert into favorite values (default, #{flabel},#{furl},#{fdesc},#{ftags})")
    @Options(useGeneratedKeys = true,keyColumn = "fid",keyProperty = "fid")
    int insert(Favorite favorite);

    /**
     * 更新一条记录
     * @param favorite
     * @return
     */
    @Update("update favorite set flabel = #{flabel},furl = #{furl},fdesc = #{fdesc},tags = #{ftags} where fid = #{fid}")
    int updateCount(Favorite favorite);

    /**
     * 查询所有标签
     * @return
     */
    @Select("select * from favorite")
    List<Favorite> selectAll();

    /**
     * 查询所有空标签
     * @return
     */
    @Select("select * from favorite where tags is null")
    List<Favorite> selectNoTags();

    /**
     * 通过id查询标签
     * @param id
     * @return
     */
    @Select("select * from favorite a join tagfavorite b on a.fid = b.fid where b.tid = #{tid}")
    List<Favorite> selectById(int id);
}
