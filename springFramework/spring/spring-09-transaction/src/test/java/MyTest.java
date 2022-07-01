import com.mu.mapper.UserMapper;
import com.mu.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test() throws Exception {
        ApplicationContext Context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = Context.getBean("userMapper",UserMapper.class);

        List<User> userList = userMapper.selectUser();
        for (User user : userList){
            System.out.println(user);
        }
    }
}
