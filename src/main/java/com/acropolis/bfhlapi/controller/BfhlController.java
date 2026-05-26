package com.acropolis.bfhlapi.controller;

import com.acropolis.bfhlapi.dto.BfhlRequest;
import com.acropolis.bfhlapi.dto.BfhlResponse;
import com.acropolis.bfhlapi.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*") // Allow frontend apps to communicate with the API
public class BfhlController {

    private final BfhlService bfhlService;

    @Value("${app.user.id}")
    private String userId;

    @Value("${app.user.email}")
    private String email;

    @Value("${app.user.roll-number}")
    private String rollNumber;

    @Autowired
    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<BfhlResponse> processArray(@RequestBody BfhlRequest request) {
        try {
            BfhlResponse response = bfhlService.processRequest(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle exceptions gracefully by returning is_success = false response
            BfhlResponse errorResponse = new BfhlResponse(
                    false,
                    userId,
                    email,
                    rollNumber,
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    "0",
                    ""
            );
            return ResponseEntity.ok(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getOperationCode() {
        Map<String, Object> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }
}
