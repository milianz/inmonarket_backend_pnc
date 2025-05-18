package org.milianz.imomarketbackend.Domain.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "available_times")
public class AvailableTime {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "time_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @NotNull(message = "El día de la semana es obligatorio")
    @Min(value = 0, message = "El día debe ser entre 0 (domingo) y 6 (sábado)")
    @Max(value = 6, message = "El día debe ser entre 0 (domingo) y 6 (sábado)")
    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @NotNull(message = "La hora de inicio es obligatoria")
    @Column(name = "start_time")
    private LocalTime startTime;

    @NotNull(message = "La hora de fin es obligatoria")
    @Column(name = "end_time")
    private LocalTime endTime;
}