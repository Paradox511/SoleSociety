package com.example.sneaker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sneaker.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
