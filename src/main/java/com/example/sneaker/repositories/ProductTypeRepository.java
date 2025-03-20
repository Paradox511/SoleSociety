package com.example.sneaker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sneaker.entities.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType,Long>{

}
