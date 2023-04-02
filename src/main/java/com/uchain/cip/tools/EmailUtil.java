package com.uchain.cip.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * 邮件发送工具类
 * */
@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送简单邮件
     * */
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

        javaMailSender.send(message);
    }

    /**
     * 发送复杂邮件
     * */
    public void sendMultipartMailMessage(String to, String subject, String text, List<File> fileList) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Constant.FROM_EMAIL);
            helper.setText(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            for (File file : fileList) {
                helper.addAttachment(file.getName(), file);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(message);
    }
}
