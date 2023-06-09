package com.uchain.cip.service.impl;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.util.Proxys;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.service.ChatGPTService;
import com.uchain.cip.tools.InterNetUtil;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;

@Service
@Slf4j
public class ChatGPTServiceImpl implements ChatGPTService {
    @Value("${chatGPT.openAiApiBaseUrl}")
    private String openAiApiBaseUrl;
    @Value("${chatGPT.openAiApiKey}")
    private String openAiApiKey;
    @Value("${chatGPT.proxyHostName}")
    private String proxyHostName;
    @Value("${chatGPT.proxyPort}")
    private int proxyPort;

    /**
     * 实现方法1
     * */
    @Override
    public String putQuest1(String prompt) {
        log.info("用户提问：" + prompt);

        String context = null;
        try {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostName, proxyPort));
            OpenAiClient openAiClient = OpenAiClient.builder()
                    .apiKey(openAiApiKey)
                    .connectTimeout(100)
                    .writeTimeout(100)
                    .readTimeout(100)
                    .interceptor(Collections.singletonList(httpLoggingInterceptor))
//                    .proxy(proxy)
                    .apiHost(openAiApiBaseUrl)
                    .build();
//        openAiClient.model("gpt-3.5-turbo");
            CompletionResponse completions = openAiClient.completions(prompt);

            StringBuilder stringBuilder = new StringBuilder();
            for (Choice choice : completions.getChoices()) {
                stringBuilder.append(choice);
            }
            context = stringBuilder.toString();
            String prefix = "Choice(text= ";
            String suffix = ", index=0, logprobs=null, finishReason=stop)";
            context = context.substring(prefix.length());
            context = context.substring(0, context.length() - suffix.length());
        } catch (Exception e) {
            return ResultEnum.CONNECT_TIME_OUT.getMessage();
        }

        log.info("ChatGPT回答：" + context);

        return context;
    }

    /**
     * 实现方法2
     * */
    @Override
    public String putQuest2(String prompt) {
        log.info("用户提问：" + prompt);

        Message res = null;
        try {
            //国内需要代理 国外不需要
//            String proxyHostIp = InterNetUtil.domainNameToIp(proxyHostName);
//            Proxy proxy = Proxys.http(proxyHostIp, proxyPort);

            ChatGPT chatGPT = ChatGPT.builder()
                    .apiKey(openAiApiKey)
//                    .proxy(proxy)
                    .timeout(3000)
                    //反向代理地址
                    .apiHost(openAiApiBaseUrl)
                    .build()
                    .init();

            Message message = Message.of(prompt);

            ChatCompletion chatCompletion = ChatCompletion.builder()
                    .model(ChatCompletion.Model.GPT_3_5_TURBO_0301.getName())
                    .messages(Collections.singletonList(message))
                    .maxTokens(3000)
                    .temperature(0.9)
                    .build();
            ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
            res = response.getChoices().get(0).getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEnum.CONNECT_TIME_OUT.getMessage();
        }

        log.info("ChatGPT回答：" + res.getContent());

        return res.getContent();
    }
}
