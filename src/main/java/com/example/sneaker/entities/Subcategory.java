package com.example.sneaker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Subcategories")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private Long subcategoryId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "subcategory_name", nullable = false)
    private String name;

    // Getters and setters
}