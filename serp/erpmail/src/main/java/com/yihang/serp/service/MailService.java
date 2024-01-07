package com.yihang.serp.service;

import org.springframework.scheduling.annotation.Scheduled;

public interface MailService {

    @Scheduled(cron="*/6 * * * * ?")
    void sendSimpleMail(String to, String subject, String content);
}
