package com.takanadev.domain;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Data // Lombok: getters, setters, toString, equals
@NoArgsConstructor
@AllArgsConstructor
@Serdeable.Deserializable
@Serdeable.Serializable
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Double cost;
}