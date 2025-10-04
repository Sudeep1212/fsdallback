package in.chill.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import in.chill.main.dto.ChatbotMessageRequest;
import in.chill.main.dto.ChatbotMessageResponse;
import in.chill.main.services.GeminiAIService;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST Controller for chatbot functionality
 * Handles communication between frontend and Gemini AI service
 */
@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201", "http://localhost:3000"})
public class ChatbotController {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotController.class);

    @Autowired
    private GeminiAIService geminiAIService;

    /**
     * Handle chatbot message requests
     */
    @PostMapping("/message")
    public Mono<ResponseEntity<ChatbotMessageResponse>> sendMessage(@Valid @RequestBody ChatbotMessageRequest request) {
        logger.info("Received chatbot message request for session: {}", request.getSessionId());
        
        // Validate request
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            ChatbotMessageResponse errorResponse = ChatbotMessageResponse.error(
                "Message cannot be empty", request.getSessionId()
            );
            return Mono.just(ResponseEntity.badRequest().body(errorResponse));
        }

        // Check if Gemini API is configured
        if (!geminiAIService.isConfigured()) {
            logger.error("Gemini API is not properly configured");
            ChatbotMessageResponse errorResponse = ChatbotMessageResponse.error(
                "AI service is not available. Please contact support.", request.getSessionId()
            );
            return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse));
        }

        // Process the message with Gemini AI
        return geminiAIService.sendMessage(request.getMessage(), request.getContext(), request.getSessionId())
                .map(reply -> {
                    ChatbotMessageResponse response = ChatbotMessageResponse.success(reply, request.getSessionId());
                    logger.info("Successfully processed chatbot request for session: {}", request.getSessionId());
                    return ResponseEntity.ok(response);
                })
                .onErrorResume(error -> {
                    logger.error("Error processing chatbot message: ", error);
                    ChatbotMessageResponse errorResponse = ChatbotMessageResponse.error(
                        error.getMessage(), request.getSessionId()
                    );
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
                });
    }

    /**
     * IMMEDIATE streaming endpoint with progressive response (GET version for SSE)
     * Starts streaming INSTANTLY while Gemini processes in background
     */
    @GetMapping(value = "/stream-words-get", produces = "text/event-stream;charset=UTF-8")
    public SseEmitter streamWordsGet(
            @RequestParam String message,
            @RequestParam(required = false) String sessionId,
            @RequestParam(required = false) String context) {
        
        logger.info("Starting IMMEDIATE progressive streaming (GET) for session: {}", sessionId);
        
        SseEmitter emitter = new SseEmitter(60000L); // 60 second timeout
        
        // Parse context if provided
        Map<String, Object> tempContextMap = null;
        if (context != null && !context.trim().isEmpty()) {
            try {
                tempContextMap = new HashMap<>();
                tempContextMap.put("context", context);
            } catch (Exception e) {
                logger.warn("Failed to parse context: {}", context);
                tempContextMap = null;
            }
        }
        final Map<String, Object> contextMap = tempContextMap;
        
        // Start immediate streaming in background thread
        new Thread(() -> {
            try {
                logger.info("🚀 STARTING IMMEDIATE STREAMING");
                
                // IMMEDIATE START: Send first character instantly to trigger frontend bubble
                emitter.send(SseEmitter.event().data(""));
                Thread.sleep(50); // Brief pause for visual feedback
                
                // Now get response from Gemini and stream progressively
                StringBuilder fullResponse = new StringBuilder();
                
                geminiAIService.sendMessageStream(message, contextMap, sessionId)
                    .doOnNext(chunk -> {
                        logger.info("Received chunk: {}", chunk);
                        fullResponse.append(chunk);
                    })
                    .doOnComplete(() -> {
                        // Stream the complete response character by character for smooth effect
                        String completeText = fullResponse.toString();
                        logger.info("Starting progressive streaming of {} characters", completeText.length());
                        
                        // Stream in larger chunks instead of character-by-character
                        int chunkSize = 50; // <--- defined here, adjust as needed (50-200 chars is reasonable)
                        for (int i = 0; i < completeText.length(); i += chunkSize) {
                            try {
                                String chunk = completeText.substring(i, Math.min(i + chunkSize, completeText.length()));
                                emitter.send(SseEmitter.event().data(chunk));

                                // Small random delay for a smooth streaming feel
                                Thread.sleep(20 + (int)(Math.random() * 20)); // 20 - 40 ms
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                                logger.error("Thread interrupted during progressive streaming", ie);
                                emitter.completeWithError(ie);
                                return;
                            } catch (Exception e) {
                                logger.error("Error sending chunk", e);
                                emitter.completeWithError(e);
                                return;
                            }
                        }

                        
                        // Send completion signal
                        try {
                            emitter.send(SseEmitter.event().data("[DONE]"));
                        } catch (Exception e) {
                            logger.error("Error sending completion signal", e);
                        }
                        
                        emitter.complete();
                        logger.info("✅ PROGRESSIVE STREAMING COMPLETE");
                    })
                    .doOnError(error -> {
                        logger.error("❌ Progressive streaming error", error);
                        try {
                            emitter.send(SseEmitter.event().data("I apologize, but I encountered an error while processing your request."));
                            emitter.send(SseEmitter.event().data("[DONE]"));
                        } catch (Exception e) {
                            logger.error("Error sending error message", e);
                        }
                        emitter.completeWithError(error);
                    })
                    .blockLast(); // Wait for completion
                    
            } catch (Exception e) {
                logger.error("❌ IMMEDIATE streaming error", e);
                try {
                    emitter.send(SseEmitter.event().data("I'm sorry, I encountered an error."));
                    emitter.send(SseEmitter.event().data("[DONE]"));
                } catch (Exception ex) {
                    logger.error("Error sending error message", ex);
                }
                emitter.completeWithError(e);
            }
        }).start();
        
        return emitter;
    }

    /**
     * Word-by-word streaming endpoint for ChatGPT-like effect
     */
    @PostMapping(value = "/stream-words", produces = "text/event-stream;charset=UTF-8")
    public SseEmitter streamWords(@Valid @RequestBody ChatbotMessageRequest request) {
        logger.info("Starting word-by-word streaming for session: {}", request.getSessionId());
        
        SseEmitter emitter = new SseEmitter(60000L); // 60 second timeout
        
        // Start streaming in background thread
        new Thread(() -> {
            try {
                // Collect all chunks first, then stream word by word
                StringBuilder fullResponse = new StringBuilder();
                
                geminiAIService.sendMessageStream(request.getMessage(), request.getContext(), request.getSessionId())
                    .doOnNext(chunk -> {
                        logger.info("Received chunk: {}", chunk);
                        fullResponse.append(chunk);
                    })
                    .doOnComplete(() -> {
                        // Now stream word by word
                        String completeText = fullResponse.toString();
                        String[] words = completeText.split(" ");
                        
                        logger.info("Starting word-by-word streaming of {} words", words.length);
                        
                        for (int i = 0; i < words.length; i++) {
                            try {
                                String word = words[i] + (i < words.length - 1 ? " " : "");
                                emitter.send(SseEmitter.event().data(word));
                                Thread.sleep(80); // Small delay between words
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                                logger.error("Thread interrupted during word streaming", ie);
                                emitter.completeWithError(ie);
                                return;
                            } catch (Exception e) {
                                logger.error("Error sending word", e);
                                emitter.completeWithError(e);
                                return;
                            }
                        }
                        
                        emitter.complete();
                        logger.info("Word-by-word streaming complete");
                    })
                    .doOnError(error -> {
                        logger.error("Stream error", error);
                        emitter.completeWithError(error);
                    })
                    .blockLast(); // Wait for completion
                    
            } catch (Exception e) {
                logger.error("Word streaming error", e);
                emitter.completeWithError(e);
            }
        }).start();
        
        return emitter;
    }

    /**
     * Simple SSE streaming endpoint - sends each chunk immediately
     */
    @PostMapping(value = "/stream-simple", produces = "text/event-stream;charset=UTF-8")
    public SseEmitter streamSimple(@Valid @RequestBody ChatbotMessageRequest request) {
        logger.info("Starting simple SSE stream for session: {}", request.getSessionId());
        
        SseEmitter emitter = new SseEmitter(30000L); // 30 second timeout
        
        // Start streaming in background thread
        new Thread(() -> {
            try {
                // Send each chunk immediately as it arrives from Gemini
                geminiAIService.sendMessageStream(request.getMessage(), request.getContext(), request.getSessionId())
                    .doOnNext(chunk -> {
                        try {
                            logger.info("Sending chunk: {}", chunk);
                            emitter.send(SseEmitter.event().data(chunk));
                        } catch (Exception e) {
                            logger.error("Error sending chunk", e);
                            emitter.completeWithError(e);
                        }
                    })
                    .doOnComplete(() -> {
                        logger.info("Stream complete, closing SSE");
                        emitter.complete();
                    })
                    .doOnError(error -> {
                        logger.error("Stream error", error);
                        emitter.completeWithError(error);
                    })
                    .blockLast(); // Wait for completion
                    
            } catch (Exception e) {
                logger.error("SSE streaming error", e);
                emitter.completeWithError(e);
            }
        }).start();
        
        return emitter;
    }

    /**
     * Handle chatbot message requests with streaming support
     */
    @PostMapping(value = "/stream", produces = "text/plain;charset=UTF-8")
    public Flux<String> sendMessageStream(@Valid @RequestBody ChatbotMessageRequest request) {
        logger.info("Received chatbot streaming message request for session: {}", request.getSessionId());
        
        // Validate request
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            return Flux.just("ERROR: Message cannot be empty");
        }

        // Check if Gemini API is configured
        if (!geminiAIService.isConfigured()) {
            logger.error("Gemini API is not properly configured");
            return Flux.just("ERROR: AI service is not available. Please contact support.");
        }

        // Process the message with Gemini AI streaming
        return geminiAIService.sendMessageStream(request.getMessage(), request.getContext(), request.getSessionId())
                .doOnNext(chunk -> logger.info("Streaming chunk for session {}: '{}'", request.getSessionId(), chunk))
                .onErrorResume(error -> {
                    logger.error("Error processing streaming chatbot message: ", error);
                    return Flux.just("ERROR: " + error.getMessage());
                });
    }

    /**
     * Test endpoint to collect all streaming chunks into a single response
     */
    @PostMapping(value = "/stream-test", produces = "application/json")
    public Mono<Map<String, Object>> testStreamingCollection(@Valid @RequestBody ChatbotMessageRequest request) {
        logger.info("Received chatbot streaming test request for session: {}", request.getSessionId());
        
        // Validate request
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Message cannot be empty");
            return Mono.just(error);
        }

        // Check if Gemini API is configured
        if (!geminiAIService.isConfigured()) {
            logger.error("Gemini API is not properly configured");
            Map<String, Object> error = new HashMap<>();
            error.put("error", "AI service is not available");
            return Mono.just(error);
        }

        // Collect all streaming chunks
        return geminiAIService.sendMessageStream(request.getMessage(), request.getContext(), request.getSessionId())
                .doOnNext(chunk -> logger.info("Collected chunk: '{}'", chunk))
                .collectList()
                .map(chunks -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("chunks", chunks);
                    result.put("totalChunks", chunks.size());
                    result.put("combinedText", String.join("", chunks));
                    result.put("sessionId", request.getSessionId());
                    return result;
                })
                .onErrorResume(error -> {
                    logger.error("Error in streaming test: ", error);
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("error", error.getMessage());
                    return Mono.just(errorResult);
                });
    }

    /**
     * DUMMY TEST ENDPOINT: Simple streaming test without Gemini
     * Streams "Hello " -> "World " -> "!" with 1-second delays
     */
    @GetMapping(value = "/test-stream", produces = "text/event-stream;charset=UTF-8")
    public SseEmitter testSimpleStream() {
        logger.info("🧪 Starting DUMMY test stream");
        
        SseEmitter emitter = new SseEmitter(10000L); // 10 second timeout
        
        // Start streaming in background thread
        new Thread(() -> {
            try {
                String[] testChunks = {"Hello ", "World ", "!"};
                
                for (String chunk : testChunks) {
                    logger.info("📤 Sending test chunk: '{}'", chunk);
                    emitter.send(SseEmitter.event().data(chunk));
                    Thread.sleep(1000); // 1 second delay between chunks
                }
                
                // Send completion signal
                emitter.send(SseEmitter.event().data("[DONE]"));
                emitter.complete();
                logger.info("✅ DUMMY test stream complete");
                
            } catch (Exception e) {
                logger.error("❌ Test stream error", e);
                emitter.completeWithError(e);
            }
        }).start();
        
        return emitter;
    }

    /**
     * Health check endpoint for chatbot service
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "ChatBot");
        health.put("timestamp", System.currentTimeMillis());
        health.put("geminiConfigured", geminiAIService.isConfigured());
        
        return ResponseEntity.ok(health);
    }

    /**
     * Clear session context
     */
    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<Map<String, String>> clearSession(@PathVariable String sessionId) {
        logger.info("Clearing session context for: {}", sessionId);
        
        geminiAIService.clearSessionContext(sessionId);
        
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Session cleared successfully");
        response.put("sessionId", sessionId);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get chatbot capabilities and information
     */
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getChatbotInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "FestFlex Assistant");
        info.put("version", "1.0.0");
        info.put("capabilities", List.of(
            "Event information and registration help",
            "Platform navigation assistance", 
            "General support and FAQs",
            "Feature explanations",
            "Contact information"
        ));
        info.put("maxMessageLength", 500);
        info.put("isAvailable", geminiAIService.isConfigured());
        
        return ResponseEntity.ok(info);
    }
}