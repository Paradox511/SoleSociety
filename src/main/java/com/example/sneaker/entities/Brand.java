package com.example.sneaker.entities;

import jakarta.annotation.*;
import jakarta.persistence.*;


@Entity
@Table(name = "Brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "brand_slogan")
    private String slogan;

    @Column(name = "website")
    private String website;

    public Brand() {}

	public Brand(Long brandId, String brandName, String logoUrl, String slogan, String website) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.logoUrl = logoUrl;
		this.slogan = slogan;
		this.website = website;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
    
    // Getters and setters
	
}