package com.controller;

import com.dto.ApiResponse;
import com.dto.ChatRequest;
import com.dto.ChatResponse;
import com.service.AiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/chat")
    public ApiResponse<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        String result = aiService.generateResponse(request.getMessage());
        return ApiResponse.<ChatResponse>builder().success(true).data(new ChatResponse(result)).build();
    }
}