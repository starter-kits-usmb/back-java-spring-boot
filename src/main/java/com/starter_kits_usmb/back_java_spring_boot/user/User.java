package com.starter_kits_usmb.back_java_spring_boot.user;

import com.starter_kits_usmb.back_java_spring_boot.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Column(name = "password")
    @NotBlank
    @Size(min = 3, max = 120)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", nullable=false)
    private Role role;
}
