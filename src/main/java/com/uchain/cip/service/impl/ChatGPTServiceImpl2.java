package com.uchain.cip.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uchain.cip.service.ChatGPTService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ChatGPTServiceImpl2 implements ChatGPTService {
    private String apiEndpoint = "https://api.openai.com/v1/completions";
    @Value("${chatGPT.openAiApiKey}")
    private String openAiApiKey;
    @Value("${chatGPT.proxyHostName}")
    private String proxyHostName;
    @Value("${chatGPT.proxyPort}")
    private int proxyPort;

    public String putQuest(String prompt) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS);
        // Set proxy configuration here
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostName, proxyPort));
        builder.proxy(proxy);
        OkHttpClient client = builder.build();

        MediaType mediaType = MediaType.parse("application/json");
        ObjectMapper objectMapper = new ObjectMapper();

        List<String> stopSequences = new ArrayList<>();
        stopSequences.add("\n");

        OpenAIResponse openAIResponse = null;
        try {
            RequestBody body = RequestBody.create(mediaType,
                    objectMapper.writeValueAsString(new OpenAIRequest(prompt, 5, stopSequences)));
            Request request = new Request.Builder()
                    .url(apiEndpoint)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + openAiApiKey)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            openAIResponse = objectMapper.readValue(responseBody, OpenAIResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        for (OpenAIResponse.Choice choice : openAIResponse.getChoices()) {
            if (choice != null && choice.getText() != null) {
                sb.append(choice.getText());
            }
        }
        return sb.toString();
    }

    static class OpenAIRequest {
        private String prompt;
        private int max_tokens;
        private List<String> stop;

        public OpenAIRequest(String prompt, int maxTokens, List<String> stopSequences) {
            this.prompt = prompt;
            this.max_tokens = maxTokens;
            this.stop = stopSequences;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public int getMax_tokens() {
            return max_tokens;
        }

        public void setMax_tokens(int max_tokens) {
            this.max_tokens = max_tokens;
        }

        public List<String> getStop() {
            return stop;
        }

        public void setStop(List<String> stop) {
            this.stop = stop;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class OpenAIResponse {
        private List<Choice> choices;
        private Map<String, Object> error;

        public List<Choice> getChoices() {
            return choices;
        }

        public void setChoices(List<Choice> choices) {
            this.choices = choices;
        }

        public Map<String, Object> getError() {
            return error;
        }

        public void setError(Map<String, Object> error) {
            this.error = error;
        }

        static class Choice {
            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
