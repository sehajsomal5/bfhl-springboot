package com.bfhl.bfhl.controller;

import com.bfhl.bfhl.dto.ApiResponse;
import com.bfhl.bfhl.dto.BfhlRequest;
import com.bfhl.bfhl.dto.ErrorResponse;
import com.bfhl.bfhl.service.BfhlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<Object> handleBfhl(@RequestBody(required = false) BfhlRequest request) {
        if (request == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Request body is missing"));
        }

        try {
            Object data = bfhlService.processRequest(request);
            if (data == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse(
                                "Invalid request: exactly one of [fibonacci, prime, lcm, hcf, AI] required"));
            }

            ApiResponse response = new ApiResponse(true, bfhlService.getOfficialEmail(), data);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal server error: " + e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse> getHealth() {
        ApiResponse response = new ApiResponse(true, bfhlService.getOfficialEmail(), null);
        return ResponseEntity.ok(response);
    }
}
