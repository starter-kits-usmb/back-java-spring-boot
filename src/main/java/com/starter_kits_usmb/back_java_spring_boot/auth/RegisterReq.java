package com.starter_kits_usmb.back_java_spring_boot.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterReq {
    @Size(min = 3, max = 100)
    @NoBannedChars(bannedChars = {' ', '"', '\'', '`'})
    private String login;

    @Size(min = 3, max = 100)
    @NoBannedChars(bannedChars = {' ', '"', '\'', '`'})
    private String password;
}
