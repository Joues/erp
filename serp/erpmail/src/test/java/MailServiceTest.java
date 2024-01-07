import com.yihang.serp.MailApplication;
import com.yihang.serp.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/7/17 23:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailApplication.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("507267107@qq.com","简单邮件自动发送测试"," Spring Boot 邮件自动发送测试！邮件发送间隔时间为: 6秒 ");
    }
}
