package org.example.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Data
@Entity(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
