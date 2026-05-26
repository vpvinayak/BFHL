package com.acropolis.bfhlapi.dto;

import java.util.List;

public class BfhlRequest {

    private List<String> data;

    // Default constructor for Jackson
    public BfhlRequest() {
    }

    public BfhlRequest(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
