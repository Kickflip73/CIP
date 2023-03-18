package com.uchain.cip.server;

import com.uchain.cip.tools.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendEmailTest {
    @Autowired
    EmailUtil emailUtil;

    @Test
    public void testSendSimpleMessageEmail() {
//        emailUtil.sendSimpleMailMessage("3065242502@qq.com", "Hello", "测试内容");
    }
}
