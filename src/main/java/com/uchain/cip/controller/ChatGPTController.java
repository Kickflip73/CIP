package com.uchain.cip.controller;
import com.uchain.cip.service.impl.ChatGPTServiceImpl;
import com.uchain.cip.service.impl.ChatGPTServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/GPT")
public class ChatGPTController {
    @Autowired
    private ChatGPTServiceImpl2 chatGPTService;

    @PostMapping
    public String getResponse(@RequestBody String userInput) throws IOException {
        return chatGPTService.putQuest(userInput);
    }
}
