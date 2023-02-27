package com.mu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mu.domain.Article;
import com.mu.mapper.ArticleMapper;
import com.mu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author MUZUKI
* @description 针对表【article】的数据库操作Service实现
* @createDate 2023-02-27 15:16:16
*/

@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int deleteArticle(int id) {
        return articleMapper.deleteArticle(id);
    }
}




