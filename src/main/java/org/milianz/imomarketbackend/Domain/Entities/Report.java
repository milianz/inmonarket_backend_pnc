package org.milianz.imomarketbackend.Domain.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "report_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_user_id", nullable = false)
    private User reporter;

    @NotBlank(message = "El motivo del reporte es obligatorio")
    private String reason;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "report_date", updatable = false)
    private LocalDateTime reportDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    // Enumeración para los estados del reporte
    public enum ReportStatus {
        PENDING, REVIEWED, RESOLVED, DISMISSED
    }
}
