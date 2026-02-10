package com.bfhl.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "is_success", "official_email", "data" })
public class ApiResponse {
    private boolean isSuccess;
    private String officialEmail;
    private Object data;

    public ApiResponse(boolean isSuccess, String officialEmail, Object data) {
        this.isSuccess = isSuccess;
        this.officialEmail = officialEmail;
        this.data = data;
    }

    // Getters and Setters
    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
