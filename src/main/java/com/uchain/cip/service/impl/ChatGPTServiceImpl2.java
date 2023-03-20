package com.uchain.cip.service.impl;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.util.Proxys;
import com.uchain.cip.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.Proxy;
import java.util.Arrays;

@Service
public class ChatGPTServiceImpl2 implements ChatGPTService {
    private String apiEndpoint = "https://api.openai.com/v1/completions";
    @Value("${chatGPT.openAiApiKey}")
    private String openAiApiKey;
    @Value("${chatGPT.proxyHostName}")
    private String proxyHostName;
    @Value("${chatGPT.proxyHostIp}")
    private String proxyHostIp;
    @Value("${chatGPT.proxyPort}")
    private int proxyPort;

    public String putQuest(String prompt) {
        Proxy proxy = Proxys.http(proxyHostIp, proxyPort);

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(openAiApiKey)
                .proxy(proxy)
                .apiHost("https://api.openai.com/") //反向代理地址
                .build()
                .init();

        String res = chatGPT.chat(prompt);
        System.out.println(res);

        return res;
    }
}
