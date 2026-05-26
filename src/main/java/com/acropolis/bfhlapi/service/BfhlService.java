package com.acropolis.bfhlapi.service;

import com.acropolis.bfhlapi.dto.BfhlRequest;
import com.acropolis.bfhlapi.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse processRequest(BfhlRequest request);
}
