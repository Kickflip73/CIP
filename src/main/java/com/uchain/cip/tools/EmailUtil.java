package com.uchain.cip.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 邮件发送工具类
 * */
@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMailMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发件人
        message.setFrom(Constant.FROM_EMAIL);
        //目的邮箱
        message.setTo(to);
        //主题
        message.setSubject(subject);
        //内容
        message.setText(text);

        mailSender.send(message);
    }
}
