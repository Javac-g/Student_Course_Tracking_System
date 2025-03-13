package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish")
    private LocalDate endDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @OneToMany(mappedBy = "course")
    private List<Task> tasks;
}
