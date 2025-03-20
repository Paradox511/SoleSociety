package com.example.sneaker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sneaker.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {

}
