import com.mu.AppConfig;
import com.mu.dao.OpRecordDao;
import com.mu.dao.biz.BankBiz;
import com.mu.pojo.Account;
import com.mu.pojo.OpRecord;
import com.mu.pojo.OpType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 19:28
 **/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class BankTest {
    @Autowired
    private OpRecordDao opRecordDao;

    @Autowired
    private BankBiz bankBiz;

    @Test
    public void testOpRecordInsert(){
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(1);
        opRecord.setOpmoney(100);
        opRecord.setOpType(OpType.TRANSFER);

        this.opRecordDao.insertOpRecord(opRecord);
    }

    @Test
    public void testOpRecordFind(){
        List<OpRecord> list = this.opRecordDao.findOpRecord(6);
        System.out.println(list);
    }

    @Test
    public void testOpRecordFind2(){
        List<OpRecord> list = this.opRecordDao.findOpRecord(6, "DEPOSITE");
        System.out.println(list);
    }

    @Test
    public void testBiz1(){
        Account a = bankBiz.findAccount(9);
        System.out.println(a);
    }

    @Test
    public void testBiz2(){
        Account a = bankBiz.openAccount(100);
        System.out.println(a);
    }

    @Test
    public void testBiz3(){
        Account a = bankBiz.withdraw(9, 10);
        System.out.println(a);
    }

    @Test
    public void testBiz4(){
        Account a = bankBiz.transfer(12, 100,1);
        System.out.println(a);
    }
}
