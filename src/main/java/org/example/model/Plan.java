package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString(exclude = {"courses", "users"})
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The 'title' cannot be empty")
    private String title;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="plan", cascade = CascadeType.REMOVE)
    private Set<Course> courses = new LinkedHashSet<>();

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(
            name="plan_user", joinColumns=@JoinColumn(name="plan_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private Set<User> users = new LinkedHashSet<>();
}
