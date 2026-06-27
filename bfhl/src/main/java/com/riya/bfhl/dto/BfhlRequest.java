package com.riya.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request DTO for the /bfhl endpoint.
 * Accepts an array of strings as input data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BfhlRequest {

    @NotNull(message = "data field must not be null")
    @JsonProperty("data")
    private List<String> data;
}
