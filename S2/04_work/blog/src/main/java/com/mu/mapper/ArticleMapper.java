package com.mu.mapper;

import com.mu.domain.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author MUZUKI
* @description 针对表【article】的数据库操作Mapper
* @createDate 2023-02-27 15:16:16
* @Entity generator.domain.Article
*/

public interface ArticleMapper extends BaseMapper<Article> {
    public int deleteArticle(int id);
}




