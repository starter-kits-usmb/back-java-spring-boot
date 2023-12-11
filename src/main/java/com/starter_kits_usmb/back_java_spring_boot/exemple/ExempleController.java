package com.starter_kits_usmb.back_java_spring_boot.exemple;

import java.util.List;

import com.starter_kits_usmb.back_java_spring_boot.exemple.dto.ExempleCreateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/exemples")
@RequiredArgsConstructor
@Tag(name = "Exemple Management", description = "Endpoints for managing exemple")
public class ExempleController {
    private final ExempleRepository exempleRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all the exemples")
    public List<Exemple> getAllExemples() {
        return exempleRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get an exemple by id")
    public Optional<Exemple> getExempleById(@PathVariable long id) {
        return exempleRepository.findById(id);
    }

    // protect the route with role admin
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new exemple")
    public Exemple createExemple(@Valid @RequestBody ExempleCreateDTO exempleDTO) {
        Exemple exemple = new Exemple();
        exemple.setTitle(exempleDTO.getTitle());
        exemple.setDescription(exempleDTO.getDescription());
        exemple.setPublished(exempleDTO.isPublished());
        return exempleRepository.save(exemple);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update the exemple")
    public Optional<Exemple> updateExemple(@Valid @RequestBody Exemple exemple) {
        if (exempleRepository.existsById(exemple.getId())) {
            return Optional.of(exempleRepository.save(exemple));
        }
        return Optional.empty();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an exemple")
    public void deleteExempleById(@PathVariable long id) {
        exempleRepository.deleteById(id);
    }
}
