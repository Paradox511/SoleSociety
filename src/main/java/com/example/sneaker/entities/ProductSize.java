package com.example.sneaker.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_sizes")
public class ProductSize {

    @Id
    @Column(name = "product_size_id")
    private UUID productSizeId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "size", nullable = false)
    private BigDecimal size;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "status", nullable = false)
    private int status;

}
