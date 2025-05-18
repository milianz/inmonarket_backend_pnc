package org.milianz.imomarketbackend.Domain.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publications")
public class Publication {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "publication_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_type_id", nullable = false)
    private PropertyType propertyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @NotBlank(message = "La dirección de la propiedad es obligatoria")
    @Column(name = "property_address")
    private String propertyAddress;

    @DecimalMin(value = "-180.0", message = "La longitud debe ser como mínimo -180.0")
    @DecimalMax(value = "180.0", message = "La longitud debe ser como máximo 180.0")
    private BigDecimal longitude;

    @DecimalMin(value = "-90.0", message = "La latitud debe ser como mínimo -90.0")
    @DecimalMax(value = "90.0", message = "La latitud debe ser como máximo 90.0")
    private BigDecimal latitude;

    @NotNull(message = "El tamaño de la propiedad es obligatorio")
    @DecimalMin(value = "1.0", message = "El tamaño debe ser mayor a 0")
    @Column(name = "property_size")
    private BigDecimal propertySize;

    @NotNull(message = "El número de habitaciones es obligatorio")
    @Min(value = 0, message = "El número de habitaciones debe ser mayor o igual a 0")
    @Column(name = "property_bedrooms")
    private Integer propertyBedrooms;

    @NotNull(message = "El número de pisos es obligatorio")
    @Min(value = 1, message = "El número de pisos debe ser al menos 1")
    @Column(name = "property_floors")
    private Integer propertyFloors;

    @Min(value = 0, message = "El número de estacionamientos debe ser mayor o igual a 0")
    @Column(name = "property_parking")
    private Integer propertyParking;

    @Column(name = "property_furnished")
    private Boolean propertyFurnished;

    @Column(name = "property_description", columnDefinition = "TEXT")
    private String propertyDescription;

    @NotNull(message = "El precio de la propiedad es obligatorio")
    @DecimalMin(value = "0.0", message = "El precio debe ser mayor o igual a 0")
    @Column(name = "property_price")
    private BigDecimal propertyPrice;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PublicationStatus status;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyImage> propertyImages;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableTime> availableTimes;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Report> reports;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visit> visits;

    // Enumeración para los estados de la publicación
    public enum PublicationStatus {
        ACTIVE, PENDING, SOLD, RENTED, INACTIVE
    }
}
