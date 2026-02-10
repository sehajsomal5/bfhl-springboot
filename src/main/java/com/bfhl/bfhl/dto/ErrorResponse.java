package com.bfhl.bfhl.dto;

public class ErrorResponse {
    private boolean isSuccess;
    private String message;

    public ErrorResponse(String message) {
        this.isSuccess = false;
        this.message = message;
    }

    // Getters and Setters
    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
