package com.example.workoutManager.domain.workout.rating;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class WorkoutRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID workoutId;

    @Column(nullable = false, unique = true)
    private UUID userId;

    @Size(min = 1, max = 5)
    @Column(nullable = false)
    private Byte value;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Boolean isPublic;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
