package com.starter_kits_usmb.back_java_spring_boot.role;

import com.starter_kits_usmb.back_java_spring_boot.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "name")
    private ERole name;
}
