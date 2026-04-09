package com.example.jwtsecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true,nullable = false)
     private String username;
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;
    private String role;
    private String email;

}
