package com.mu.favorite.biz;

import com.mu.favorite.domain.Favorite;
import com.mu.favorite.domain.Tag;
import com.mu.favorite.mapper.FavoriteMapper;
import com.mu.favorite.mapper.TagMapper;
import com.mu.favorite.mapper.TagfavoriteMapper;
import com.mu.favorite.util.MybatisHelper;
import org.apache.ibatis.session.SqlSession;

/**
 * @author : MUZUKI
 * @date : 2022-12-30 15:41
 **/

public class FavBizImpl implements FavBiz {
    @Override
    public void addFov(Favorite favorite) {
        SqlSession session = MybatisHelper.openSession();
        try{
            TagMapper tm =  session.getMapper(TagMapper.class);
            FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
            TagfavoriteMapper tfm = session.getMapper(TagfavoriteMapper.class);
            //省略字段验证
            //保存连接
            fm.insert(favorite);
            //分析ftags 字段 ： 门户,新闻,体育
            String[] tags = favorite.getFtags().split("[,，：\\-;\\s]+");
            for(String tag : tags){
                if (tm.updateCount(tag) == 0){
                    Tag t = new Tag();
                    t.setTname(tag);
                    t.setTcount(1);
                    //新增分类
                    tm.insert(t);
                    //新增中间表记录
                    tfm.insert(t.getTid(),favorite.getFid());
                }else{
                    //新增中间表记录
                    tfm.insertByTname(tag,favorite.getFid());
                }
            }
            session.commit();
        }catch (Exception e) {
            session.rollback();
        }finally {
            session.close();
        }
    }
}
