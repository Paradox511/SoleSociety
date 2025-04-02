package com.example.sneaker.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    @Column(name = "colorway")
    private String colorway;

    @Column(name = "style_code")
    private String styleCode;

    @Column(name = "image_urls")
    private String imageUrls; // Consider using a more appropriate data structure
    
   
    @Column(name ="status")
    private int status;
    
  
    @Column(name="discount")
    private int discount;

    public Product() {}
    
	public Product(UUID productId, String name, String description, Brand brand, Subcategory subcategory,
			ProductType productType, LocalDate releaseDate, BigDecimal retailPrice, String colorway, String styleCode,
			String imageUrls,int status,int discount) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.subcategory = subcategory;
		this.productType = productType;
		this.releaseDate = releaseDate;
		this.retailPrice = retailPrice;
		this.colorway = colorway;
		this.styleCode = styleCode;
		this.imageUrls = imageUrls;
		status = 1;
		discount=0;
	}

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getColorway() {
		return colorway;
	}

	public void setColorway(String colorway) {
		this.colorway = colorway;
	}

	public String getStyleCode() {
		return styleCode;
	}

	public void setStyleCode(String styleCode) {
		this.styleCode = styleCode;
	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

    // Getters and setters
    
}
