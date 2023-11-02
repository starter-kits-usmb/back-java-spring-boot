package com.starter_kits_usmb.back_java_spring_boot.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public record LoginRes (String token) {}
