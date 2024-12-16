package com.app.springpowpow.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SnsService {
    public ResponseEntity<Map<String, Object>> transferMessage(String memberPhone);

    public ResponseEntity<Map<String, Object>> sendEmailVerification(String memberEmail);
}
