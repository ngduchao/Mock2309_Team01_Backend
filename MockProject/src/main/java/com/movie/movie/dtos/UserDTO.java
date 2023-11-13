package com.movie.movie.dtos;

import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    @JsonProperty("user_id")
    @Min(value = 1, message = "User's ID must be > 0")
    private Integer UserId;

    @JsonProperty("username")
    @NotBlank
    @Size(min = 6, max = 50)
    private String username;

    @JsonProperty("email")
    @Email
    @NotBlank
    @Size(min = 6, max = 50)
    private String email;

    @JsonProperty("password")
    @NotBlank
    @Size(max = 800)
    private String password;

    @JsonProperty("firstName")
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("status")
    private Boolean status;

    public enum Role {
        Admin, Manager, User
    }
}