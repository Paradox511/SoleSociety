package com.example.sneaker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sneaker.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
