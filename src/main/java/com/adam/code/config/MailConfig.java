package com.adam.code.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮件配置类
 * @author VAIO-adam
 */

@Configuration
public class MailConfig {

    @Value("${personConfig.mail.host}")
    private String mailHost;

    @Value("${personConfig.mail.port}")
    private Integer mailPort;

    @Value("${personConfig.mail.username}")
    private String username;

    @Value("${personConfig.mail.password}")
    private String password;

    /**
     * 获取邮件发送实例
     */
    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        //  指定服务器主机名 和 端口
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        return mailSender;
    }

}
