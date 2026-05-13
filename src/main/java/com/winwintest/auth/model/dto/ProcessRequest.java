package com.winwintest.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProcessRequest(@NotBlank @Size(max = 8000) String text) {
}
