package com.uchain.cip.controller;
import com.uchain.cip.service.impl.ChatGPTServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/GPT")
@CrossOrigin
@Api(tags = "ChatGPT接口")
public class ChatGPTController {
    @Autowired
    private ChatGPTServiceImpl chatGPTService;

    @PostMapping
    @ApiOperation(value = "发送消息", notes = "发送一个消息给ChatGPT，ChatGPT回答这个消息")
    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "userInput",
                    value = "用户输入的消息",
                    example = "你好",
                    required = true,
                    paramType = "body"
            )
    )
    public String getResponse(@RequestBody String userInput) throws IOException {
        return chatGPTService.putQuest2(userInput);
    }
}
