import com.mu.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : MUZUKI
 * @date : 2023-02-27 16:09
 **/

public class mybatis {
    @Test
    public void testSM() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ArticleService articleService = applicationContext.getBean("articleService", ArticleService.class);
        try {
            int res = articleService.deleteArticle(20);
            System.out.println("res" + res);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }
}
