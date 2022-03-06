package com.yihang.serp;

import com.yihang.serp.model.MailConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/7/17 23:15
 */
@SpringBootApplication
@EnableScheduling
public class MailApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

    @Bean
    Queue queue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }
}
