package com.uchain.cip.controller;
import com.uchain.cip.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/GPT")
public class GPTController {
    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping
    public String getResponse(@RequestParam String prompt) {
        return chatGPTService.putQuest(prompt);
    }
}
