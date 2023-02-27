package com.mu.service;

import com.mu.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author MUZUKI
* @description 针对表【article】的数据库操作Service
* @createDate 2023-02-27 15:16:16
*/
public interface ArticleService extends IService<Article> {
    public int deleteArticle(int id);
}
