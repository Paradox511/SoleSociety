package com.example.sneaker.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_favorites")
@Data
public class Favorite {

    @Id
    @Column(name = "user_id")
    private UUID userId; // Direct mapping of user_id as UUID

    @Id
    @Column(name = "product_id")
    private UUID productId; // Direct mapping of product_id as UUID

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false) // Add insertable and updatable to false so that the value comes from the userid column.
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    private LocalDateTime addedAt;

    // Constructors, getters, and setters
    public Favorite() {}

    public Favorite(UUID userId, UUID productId) {
        this.userId = userId;
        this.productId = productId;
        this.addedAt = LocalDateTime.now();
    }
}