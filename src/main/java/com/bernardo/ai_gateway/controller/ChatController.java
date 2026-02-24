package com.bernardo.ai_gateway.controller;

import com.bernardo.ai_gateway.model.ChatRequest;
import com.bernardo.ai_gateway.model.ChatResponse;
import com.bernardo.ai_gateway.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    private final ChatService service;

    public ChatController(ChatService service) { this.service = service; }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("{ \"status\": \"UP\" }");
    }

    @PostMapping
    public Mono<ResponseEntity<ChatResponse>> sendMessage(
            @Valid @RequestBody ChatRequest request) {
        return service.chat(request)
            .map(ResponseEntity::ok);
    }
}
