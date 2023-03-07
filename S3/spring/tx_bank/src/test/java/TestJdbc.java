import com.mu.AppConfig;
import com.mu.dao.AccountDao;
import com.mu.pojo.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 14:43
 **/

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestJdbc {
    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private AccountDao accountDao;

    @Test
    public void test(){
        System.out.println(dataSource);
    }

    @Test
    public void testDao(){
        this.accountDao.insert(1);
    }

    @Test
    public void testInsert(){
        int res = this.accountDao.insert(10);
        System.out.println("账户编号: " + res);
    }

    @Test
    public void testFindById(){
        Account account = this.accountDao.findById(1);
        System.out.println("账户信息" + account);
    }

    @Test
    public void testFindAll(){
        List<Account> accounts = this.accountDao.findAll();
        System.out.println("账户信息" + accounts);
    }

    @Test
    public void testUpdate(){
        this.accountDao.update(1, 100);
    }

    @Test
    public void testDelete(){
        int res = this.accountDao.delete(7);
    }
}
