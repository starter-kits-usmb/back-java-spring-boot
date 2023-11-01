package com.starterkitsusmb.backspringboot.exemple.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExempleCreateDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @Size(max = 200)
    private String description;

    private boolean published;
}
