package com.starter_kits_usmb.back_java_spring_boot.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterReq {
    @NotBlank
    @Size(min = 3, max = 100)
    private String login;

    @NotBlank
    @Size(min = 3, max = 100)
    private String password;
}
