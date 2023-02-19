package com.mu.favorite.mapper;

import com.mu.favorite.domain.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author MUZUKI
* @description 针对表【tag】的数据库操作Mapper
* @createDate 2022-12-30 15:01:07
* @Entity generator.domain.Tag
*/
public interface TagMapper {

    /**
     * 插入一条记录
     * @param tag
     * @return
     */
    @Insert("insert into tag values (default, #{tname},1)")
    @Options(useGeneratedKeys = true,keyColumn = "tid",keyProperty = "tid")
    int insert(Tag tag);

    /**
     * 根据 tag 名称 修改其分类的总数
     * @param tag
     * @return 数字表示更新的行数
     */
    @Update("update tag set tcount = tcount + 1 where tname = #{tname}")
    int updateCount(String tag);

    /**
     * 查询所有标签
     * @return
     */
    @Select("select * from tag")
    @Results(
            id="rm1",
            value={
                    @Result(column="tid",property="tid",id=true),
                    @Result(column="tid",property = "fList",
                            many = @Many(select = "com.mu.favorite.mapper.FavoriteMapper.selectById"))
            }
    )
    List<Tag> selectAll();

    /**
     * 通过tname查询标签
     * @param tname
     * @return
     */
    @Select("select * from tag where tname = #{tname}")
    Tag selectByTname(String tname);
}
