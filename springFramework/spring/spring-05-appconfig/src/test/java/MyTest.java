import com.mu.config.userConfig1;
import com.mu.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        // 如果完全使用了配置方法类方式去做，我们就只能通过AnnotationConfig上下文获取容器，通过配置类的class对象加载！
        ApplicationContext context = new AnnotationConfigApplicationContext(userConfig1.class);
        User user1 = context.getBean("getUser",User.class);
        User user2 = context.getBean("user",User.class);
        System.out.println(user1.getName());
        System.out.println(user2.getName());
    }
}
