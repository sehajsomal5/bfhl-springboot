package com.bfhl.bfhl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Map;
import java.util.List;

@Service
public class AiService {

    @Value("${gemini.api.key:}")
    private String apiKey;

    private final RestClient restClient;

    public AiService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public String askAi(String question) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "Please configure Gemini API Key";
        }

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key="
                + apiKey;

        try {
            Map<String, Object> responseBody = restClient.post()
                    .uri(url)
                    .body(Map.of(
                            "contents", List.of(
                                    Map.of("parts", List.of(
                                            Map.of("text",
                                                    "Answer the following question in exactly ONE word: "
                                                            + question))))))
                    .retrieve()
                    .body(new org.springframework.core.ParameterizedTypeReference<Map<String, Object>>() {
                    });

            if (responseBody != null && responseBody.get("candidates") != null) {
                List<?> candidates = (List<?>) responseBody.get("candidates");
                if (!candidates.isEmpty()) {
                    Map<?, ?> firstCandidate = (Map<?, ?>) candidates.get(0);
                    Map<?, ?> content = (Map<?, ?>) firstCandidate.get("content");
                    List<?> parts = (List<?>) content.get("parts");
                    if (!parts.isEmpty()) {
                        Map<?, ?> firstPart = (Map<?, ?>) parts.get(0);
                        return firstPart.get("text").toString().trim();
                    }
                }
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

        return "No response from AI";
    }
}
