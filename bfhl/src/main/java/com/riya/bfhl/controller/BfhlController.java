package com.riya.bfhl.controller;

import com.riya.bfhl.dto.BfhlRequest;
import com.riya.bfhl.dto.BfhlResponse;
import com.riya.bfhl.service.BfhlService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller exposing the /bfhl endpoint.
 */
@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST /bfhl
     * Accepts a JSON body with a "data" array and returns categorized results.
     *
     * @param request validated request body
     * @return 200 OK with BfhlResponse
     */
    @PostMapping
    public ResponseEntity<BfhlResponse> processData(@Valid @RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }
}
