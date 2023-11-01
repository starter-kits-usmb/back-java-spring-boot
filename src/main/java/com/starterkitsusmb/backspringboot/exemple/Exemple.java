package com.starterkitsusmb.backspringboot.exemple;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Table(name = "exemples")
@Data
public class Exemple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @Column(name = "description")
    @Size(max = 200)
    private String description;

    @Column(name = "published")
    private boolean published;
}
