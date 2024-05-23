package com.rif.backend.Payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
    @NotBlank
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
