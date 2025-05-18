package com.newren.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProcessResult {
    @JsonProperty
    private String processId;
    @JsonProperty
    private String result;
    @JsonProperty
    private long executionTimeMs;

    public ProcessResult(String processId, String result, long executionTimeMs) {
        this.processId = processId;
        this.result = result;
        this.executionTimeMs = executionTimeMs;
    }
}
