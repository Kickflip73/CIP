package com.uchain.cip.service.impl;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.util.Proxys;
import com.uchain.cip.service.ChatGPTService;
import com.uchain.cip.tools.InterNetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.Proxy;
import java.util.Collections;
import java.util.Objects;

@Service
@Slf4j
public class ChatGPTServiceImpl2 implements ChatGPTService {
    @Value("${chatGPT.openAiApiBaseUrl}")
    private String openAiApiBaseUrl;
    @Value("${chatGPT.openAiApiKey}")
    private String openAiApiKey;
    @Value("${chatGPT.proxyHostName}")
    private String proxyHostName;
    @Value("${chatGPT.proxyPort}")
    private int proxyPort;

    public String putQuest(String prompt) {
        //国内需要代理 国外不需要
        String proxyHostIp = InterNetUtil.domainNameToIp(proxyHostName);
        if (Objects.equals(proxyHostIp, "unknown")) {
            return "代理服务器域名解析失败";
        }
        Proxy proxy = Proxys.http(proxyHostIp, proxyPort);

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(openAiApiKey)
                .proxy(proxy)
                .timeout(50000)
                .apiHost(openAiApiBaseUrl)
                .build()
                .init();

        Message message = Message.of(prompt);

        ChatCompletion chatCompletion = ChatCompletion.builder()
                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                .messages(Collections.singletonList(message))
                .maxTokens(3000)
                .temperature(0.9)
                .build();
        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
        Message res = response.getChoices().get(0).getMessage();
        System.out.println("user：" + prompt + "\nGPT：" + res.getContent());

        return res.getContent();
    }
}
