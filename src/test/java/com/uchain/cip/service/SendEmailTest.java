package com.uchain.cip.service;

import com.uchain.cip.tools.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;

@SpringBootTest
public class SendEmailTest {
    @Autowired
    EmailUtil emailUtil;

    @Test
    public void testSendSimpleMessage() {
//        emailUtil.sendSimpleMailMessage("3065242502@qq.com", "Hello", "测试内容");
    }

    @Test
    public void testSendMultipartMailMessage() {
        ArrayList<File> fileList = new ArrayList<>();

        File file = new File("classpath:/application.yml");
        fileList.add(file);
        emailUtil.sendMultipartMailMessage("3065242502@qq.com", "Hello", "测试内容", fileList);
    }
}
