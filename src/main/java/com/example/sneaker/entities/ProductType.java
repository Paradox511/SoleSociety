package com.example.sneaker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Product_type")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_type_id")
    private Long productTypeId;

    @Column(name = "product_type_name", nullable = false)
    private String name;

    public ProductType() {}
	public ProductType(Long productTypeId, String name) {
		super();
		this.productTypeId = productTypeId;
		this.name = name;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    // Getters and setters
    
}
