package org.example.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 25, message = "Last name must be in range of 3 and 25 characters")
    private String firstName;

    @NotBlank
    @Size(min =3, max = 25, message = "Last name must be in range of 3 and 25 characters")
    private String lastName;

    @Column(unique = true)
    @NotBlank
    @Pattern(regexp=".+@.+\\..+", message = "Please provide us with a valid email address")
    private String email;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    @NotBlank
    private String password;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private transient List<Plan> plans;

    @ToString.Exclude
    @OneToMany(mappedBy = "trainee")
    @Transient
    private transient List<Progress> progresses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
