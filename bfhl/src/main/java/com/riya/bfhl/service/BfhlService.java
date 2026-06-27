package com.riya.bfhl.service;

import com.riya.bfhl.dto.BfhlRequest;
import com.riya.bfhl.dto.BfhlResponse;

/**
 * Service interface for BFHL business logic.
 * Defines the contract for processing input data arrays.
 */
public interface BfhlService {

    /**
     * Processes the incoming request data and returns a categorized response.
     *
     * @param request the incoming request containing the data array
     * @return BfhlResponse with categorized numbers, alphabets, special characters, etc.
     */
    BfhlResponse processData(BfhlRequest request);
}
