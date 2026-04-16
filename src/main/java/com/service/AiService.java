package com.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    public String generateResponse(String message) {

        log.info("Processing user message: {}", message);

        try {
            String response = chatClient.prompt()
                    .system("you are a helpful AI assistant for software developers.")
                    .user(message)
                    .call()
                    .content();

            log.info("AI response generated successfully");
            return response;

        } catch (Exception e) {
            log.error("error while calling AI service", e);
            if (e.getMessage().contains("429")) {
                throw new RuntimeException("API quota exceeded. please check billing.");
            }
            throw new RuntimeException("AI service unavailable");
        }
    }
}