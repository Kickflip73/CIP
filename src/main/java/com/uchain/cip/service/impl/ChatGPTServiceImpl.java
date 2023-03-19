package com.uchain.cip.service.impl;

import com.uchain.cip.service.ChatGPTService;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {
    @Value("${chatGPT.openAiApiBaseUrl}")
    private String openAiApiBaseUrl;
    @Value("${chatGPT.openAiApiKey}")
    private String openAiApiKey;
    @Value("${chatGPT.proxyHostName}")
    private String proxyHostName;
    @Value("${chatGPT.proxyPort}")
    private int proxyPort;

    @Override
    public String putQuest(String message) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostName, proxyPort));
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey(openAiApiKey)
                .connectTimeout(100)
                .writeTimeout(100)
                .readTimeout(100)
                .interceptor(Collections.singletonList(httpLoggingInterceptor))
                .proxy(proxy)
                .apiHost(openAiApiBaseUrl)
                .build();
//        openAiClient.model("gpt-3.5-turbo");
        CompletionResponse completions = openAiClient.completions(message);

        StringBuilder stringBuilder = new StringBuilder();
        for (Choice choice : completions.getChoices()) {
            stringBuilder.append(choice);
        }
        String context = stringBuilder.toString();
        String prefix = "Choice(text= ";
        String suffix = ", index=0, logprobs=null, finishReason=stop)";
        context = context.substring(prefix.length());
        context = context.substring(0, context.length() - suffix.length());

        return context;
    }
}
