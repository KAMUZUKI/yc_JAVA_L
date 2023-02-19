package mu.favorite;

import com.mu.favorite.biz.FavBiz;
import com.mu.favorite.biz.FavBizImpl;
import com.mu.favorite.domain.Favorite;
import com.mu.favorite.domain.Tag;
import com.mu.favorite.mapper.TagMapper;
import com.mu.favorite.util.MybatisHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-12-30 14:59
 **/

public class BaseTest {

    private FavBiz favBiz = new FavBizImpl();
    @Test
    public void test1(){
        SqlSession session = MybatisHelper.openSession();
        TagMapper mapper = session.getMapper(TagMapper.class);
        List<Tag> tags = mapper.selectAll();
        Assert.assertTrue("集合不为空",!tags.isEmpty());
        Assert.assertNotNull(tags.get(0).getFList().get(0));
    }

    @Test
    public void test2(){
        Favorite f = new Favorite();
        f.setFlabel("新浪");
        f.setFurl("www.sina.com");
        f.setFdesc("国内大型门户网站");
        f.setFtags("门户,新闻,体育");
        favBiz.addFov(f);

        SqlSession session = MybatisHelper.openSession();
        TagMapper mapper = session.getMapper(TagMapper.class);
        Tag mh = mapper.selectByTname("门户");
        Tag xw = mapper.selectByTname("新闻");
        Tag ty = mapper.selectByTname("体育");
        Assert.assertEquals(2,(int)mh.getTcount());
        Assert.assertEquals(1,(int)xw.getTcount());
        Assert.assertEquals(1,(int)ty.getTcount());
    }
}
