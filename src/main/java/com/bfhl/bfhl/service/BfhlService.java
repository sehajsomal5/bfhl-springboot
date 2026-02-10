package com.bfhl.bfhl.service;

import com.bfhl.bfhl.dto.BfhlRequest;
import com.bfhl.bfhl.util.MathUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BfhlService {

    @Value("${official.email:YOUR_CHITKARA_EMAIL}")
    private String officialEmail;

    private final AiService aiService;

    public BfhlService(AiService aiService) {
        this.aiService = aiService;
    }

    public Object processRequest(BfhlRequest request) {
        if (request.getFibonacci() != null) {
            return MathUtil.getFibonacci(request.getFibonacci());
        } else if (request.getPrime() != null) {
            return MathUtil.filterPrimes(request.getPrime());
        } else if (request.getLcm() != null) {
            return MathUtil.findLCM(request.getLcm());
        } else if (request.getHcf() != null) {
            return MathUtil.findGCD(request.getHcf());
        } else if (request.getAi() != null) {
            return aiService.askAi(request.getAi());
        }
        return null;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }
}
