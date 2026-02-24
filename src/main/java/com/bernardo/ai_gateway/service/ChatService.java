package com.bernardo.ai_gateway.service;

import com.bernardo.ai_gateway.client.OllamaClient;
import com.bernardo.ai_gateway.model.ChatRequest;
import com.bernardo.ai_gateway.model.ChatResponse;
import com.bernardo.ai_gateway.model.OllamaRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class ChatService {
    private final OllamaClient ollamaClient;
    @Value("${ai.engine.model}")
    private String defaultModel;
    public ChatService(WebClient webClient) {
        this.ollamaClient = new OllamaClient(webClient);
    }

    /**
     * Process a chat request and return the AI response.
     *
     * @param request the validated request from the controller
     * @return a Mono carrying the ChatResponse
     */
    public Mono<ChatResponse> chat(ChatRequest request) {
        long start = System.currentTimeMillis();

        String model;
        if (request.model() != null && !request.model().isEmpty()) {
            model = request.model();
        } else {
            model = this.defaultModel;
        }

        OllamaRequest ollamaRequest = new OllamaRequest(model, request.prompt(), false);

        Mono<String> response = ollamaClient.generate(ollamaRequest);

        return response
            .map(text -> ChatResponse.of(text, model, System.currentTimeMillis() - start));
    }
}