package org.example.reservasapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {
    private String username;
    private String password;
}

/*
 * Alternatively, you could use a Java 14 record:
 * public record LoginRequestDTO(String username, String password) { }
 * With this single line of code, Java automatically generates:
 * Constructor
 * Getters (username() and password())
 * equals() and hashCode()
 * toString()
 */