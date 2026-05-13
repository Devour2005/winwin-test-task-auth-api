package com.winwintest.auth.controller;

import com.winwintest.auth.model.dto.ProcessRequest;
import com.winwintest.auth.model.dto.ProcessResponse;
import com.winwintest.auth.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProcessController {

    private final ProcessService processService;

    @PostMapping("/process")
    public ProcessResponse process(@RequestBody ProcessRequest request) {
        return processService.process(request.text());
    }
}
