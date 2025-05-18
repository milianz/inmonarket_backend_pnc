package org.milianz.imomarketbackend.Domain.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"neighborhood", "municipality", "department"})
})
public class Location {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "location_id")
    private UUID id;

    @NotBlank(message = "El barrio es obligatorio")
    private String neighborhood;

    @NotBlank(message = "El municipio es obligatorio")
    private String municipality;

    @NotBlank(message = "El departamento es obligatorio")
    private String department;

    @OneToMany(mappedBy = "location")
    private List<Publication> publications;
}