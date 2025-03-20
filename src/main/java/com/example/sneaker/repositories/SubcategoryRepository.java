package com.example.sneaker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sneaker.entities.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {

}
