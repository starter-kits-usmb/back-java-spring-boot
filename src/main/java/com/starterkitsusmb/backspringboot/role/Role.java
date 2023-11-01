package com.starterkitsusmb.backspringboot.role;

import com.starterkitsusmb.backspringboot.user.User;
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

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "roles")
    private Set<User> users;
}
