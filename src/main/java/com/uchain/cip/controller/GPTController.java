package com.uchain.cip.controller;
import com.uchain.cip.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/GPT")
public class GPTController {
    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping
    public String getResponse(@RequestBody String userInput) {
        return chatGPTService.putQuest(userInput);
    }
}
