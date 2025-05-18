package org.milianz.imomarketbackend.Domain.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "visit_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_user_id", nullable = false)
    private User visitor;

    @NotNull(message = "La fecha de visita es obligatoria")
    @FutureOrPresent(message = "La fecha de visita debe ser hoy o en el futuro")
    @Column(name = "visit_date")
    private LocalDate visitDate;

    @NotNull(message = "La hora de visita es obligatoria")
    @Column(name = "visit_time")
    private LocalTime visitTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Enumeración para los estados de la visita
    public enum VisitStatus {
        PENDING, CONFIRMED, COMPLETED, CANCELLED
    }
}