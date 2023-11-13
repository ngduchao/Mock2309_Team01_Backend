package com.movie.movie.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    @Size(min = 6, max = 50)
    private String username;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    @Email
    @Size(min = 6, max = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 800)
    @Size(max = 800)
    private String password;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.User;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public enum Role {
        Admin, Manager, User
    }
}