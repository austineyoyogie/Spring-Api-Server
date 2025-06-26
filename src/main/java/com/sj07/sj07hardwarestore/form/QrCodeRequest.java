package com.sj07.sj07hardwarestore.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QrCodeRequest {
    @NotEmpty(message = "User ID cannot be empty or null")
    private String userId;
    @NotEmpty(message = "QR code cannot be empty or null")
    private String qrCode;
}
