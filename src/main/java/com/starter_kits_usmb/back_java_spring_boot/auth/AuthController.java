package com.starter_kits_usmb.back_java_spring_boot.auth;


import com.starter_kits_usmb.back_java_spring_boot.exception.AlreadyExistsException;
import com.starter_kits_usmb.back_java_spring_boot.exception.InvalidAuthHeaderException;
import com.starter_kits_usmb.back_java_spring_boot.security.jwt.JwtUtils;
import com.starter_kits_usmb.back_java_spring_boot.role.ERole;
import com.starter_kits_usmb.back_java_spring_boot.role.Role;
import com.starter_kits_usmb.back_java_spring_boot.role.RoleRepository;
import com.starter_kits_usmb.back_java_spring_boot.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification", description = "Endpoints for user authentication and registration")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user")
    @ResponseStatus(HttpStatus.OK)
    public LoginRes authenticateUser(@Valid @RequestBody LoginReq loginReq) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getLogin(), loginReq.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return new LoginRes(jwt);
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterRes registerUser(@Valid @RequestBody RegisterReq register) {
        if (userRepository.existsByUsername(register.getLogin())) {
            throw new AlreadyExistsException("User", "username", register.getLogin());
        }

        User user = new User();
        user.setUsername(register.getLogin());
        user.setPassword(encoder.encode(register.getPassword()));

        Role role = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        user.setRole(role);
        userRepository.save(user);
        return new RegisterRes(user.getId(), user.getUsername());
    }

    @GetMapping
    @Operation(summary = "Verify if the token is valid")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void verifyToken(@RequestHeader(name = "Authorization") String authorization) {
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            if (jwtUtils.validateJwtToken(token)) {
                return;
            }
        }
        throw new InvalidAuthHeaderException();
    }
}
