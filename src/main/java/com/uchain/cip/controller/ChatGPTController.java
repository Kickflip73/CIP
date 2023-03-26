package com.uchain.cip.controller;
import com.uchain.cip.service.impl.ChatGPTServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/GPT")
@CrossOrigin
public class ChatGPTController {
    @Autowired
    private ChatGPTServiceImpl chatGPTService;

    @PostMapping
    public String getResponse(@RequestBody String userInput) throws IOException {
        return chatGPTService.putQuest2(userInput);
    }
}
