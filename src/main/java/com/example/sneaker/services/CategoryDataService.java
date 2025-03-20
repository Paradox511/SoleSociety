package com.example.sneaker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sneaker.entities.Brand;
import com.example.sneaker.entities.CategoryItem;
import com.example.sneaker.repositories.BrandRepository;

@Service
public class CategoryDataService {

	@Autowired
	private BrandRepository brandRepository;
	
	public List<Brand> getBrandsData(){
		return brandRepository.findAll();
	}
}
