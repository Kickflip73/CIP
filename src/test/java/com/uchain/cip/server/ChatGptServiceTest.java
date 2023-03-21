package com.uchain.cip.server;

import com.uchain.cip.service.ChatGPTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChatGptServiceTest {
    @Autowired
    ChatGPTService chatGPTService;

    @Test
    public void testPutQuest() {
        String prompt = "你好";
        String message1 = chatGPTService.putQuest1(prompt);
        String message2 = chatGPTService.putQuest2(prompt);

        System.out.println(message1 + "\n" + message2);
    }

}
