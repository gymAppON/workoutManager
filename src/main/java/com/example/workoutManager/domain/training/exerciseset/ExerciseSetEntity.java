package com.example.workoutManager.domain.training.exerciseset;

import com.example.workoutManager.shared.enums.WeightUnitEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class ExerciseSetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID userExerciseId;

    @Column(nullable = false)
    private Integer setNumber;

    @Column
    private Float weight;

    @Enumerated(EnumType.STRING)
    @Column
    private WeightUnitEnum weightUnit;

    @Column
    private Integer reps;

    @Column
    private Integer distance;

    @Column
    private Integer duration;

    @Column
    private Integer effort;

    @Column
    private String notes;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
