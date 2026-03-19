package com.example.workoutManager.domain.workout;

import com.example.workoutManager.shared.enums.MuscleGroupEnum;
import com.example.workoutManager.shared.enums.VisibilityEnum;
import com.example.workoutManager.shared.enums.WorkoutLevelEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class WorkoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column
    private UUID userId;

    @Column(nullable = false)
    private String name;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private MuscleGroupEnum muscleGroup;

    @Enumerated(EnumType.STRING)
    private WorkoutLevelEnum level;

    @Enumerated(EnumType.STRING)
    private VisibilityEnum visibility;

    @Column(nullable = false)
    private List<UUID> exerciseIds;

    @Column(nullable = false)
    private Boolean isValidated = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
