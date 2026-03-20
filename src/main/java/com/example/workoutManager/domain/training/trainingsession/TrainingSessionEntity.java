package com.example.workoutManager.domain.training.trainingsession;

import com.example.workoutManager.shared.enums.TrainingTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class TrainingSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column
    private UUID workoutId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrainingTypeEnum type;

    @Column
    private String notes;

    @Column
    private Instant startedAt;

    @Column
    private Instant endAt;

    @Column
    private Instant plannedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
