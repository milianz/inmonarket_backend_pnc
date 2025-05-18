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
@Table(name = "property_types")
public class PropertyType {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "property_type_id")
    private UUID id;

    @NotBlank(message = "El nombre del tipo de propiedad es obligatorio")
    @Column(name = "type_name", unique = true)
    private String typeName;

    @OneToMany(mappedBy = "propertyType")
    private List<Publication> publications;
}