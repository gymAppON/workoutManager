package com.example.workoutManager.domain.training.userexercise;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class UserExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column (nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID trainingSessionId;

    @Column(nullable = false)
    private UUID exerciseId;

    @Column
    private Integer rate;

    @Column
    private String notation;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
