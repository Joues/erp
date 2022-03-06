package com.yihang.serp.task;

import com.yihang.serp.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/7/17 23:09
 */
@Component
@Slf4j
public class SchedulerTask {

    private int count=0;

    @Autowired
    private MailService mailService;

    @Scheduled(cron="*/6 * * * * ?")
    @Autowired
    @RabbitListener(queues = "yihang.mail")
    private void testSimpleMail() throws Exception{
//        System.out.println("this is scheduler task runing  "+(count++));
        log.info("this is scheduler task runing  "+(count++));
        mailService.sendSimpleMail("507267107@qq.com", "简单邮件自动发送测试", " Spring Boot 邮件自动发送测试！邮件发送间隔时间为: 6秒 请注意查收！");
    }

}