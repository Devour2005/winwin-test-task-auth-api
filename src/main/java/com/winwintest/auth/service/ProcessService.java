package com.winwintest.auth.service;

import com.winwintest.auth.model.dto.ProcessResponse;
import com.winwintest.auth.model.dto.TransformRequest;
import com.winwintest.auth.model.dto.TransformResponse;
import com.winwintest.auth.model.ProcessingLog;
import com.winwintest.auth.repository.ProcessingLogRepository;
import com.winwintest.auth.security.AppUserDetails;
import com.winwintest.auth.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessService {

    private final RestClient dataApiRestClient;
    private final ProcessingLogRepository processingLogRepository;

    @Transactional
    public ProcessResponse process(String text) {
        User user = currentUser();
        TransformResponse transform;
        try {
            transform = dataApiRestClient
                    .post()
                    .uri("/api/transform")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new TransformRequest(text))
                    .retrieve()
                    .body(TransformResponse.class);
            log.info("TransformResponse {}", transform);
        } catch (RestClientException ex) {
            throw new IllegalStateException("data-api unavailable: " + ex.getMessage(), ex);
        }

        if (transform == null || transform.result() == null) {
            throw new IllegalStateException("Invalid response from data-api");
        }

        ProcessingLog log = new ProcessingLog();
        log.setUser(user);
        log.setInputText(text);
        log.setOutputText(transform.result());
        processingLogRepository.save(log);

        return new ProcessResponse(transform.result());
    }

    private User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof AppUserDetails details)) {
            throw new IllegalStateException("Not authenticated");
        }
        return details.getUser();
    }
}
