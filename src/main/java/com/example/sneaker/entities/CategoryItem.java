package com.example.sneaker.entities;

public class CategoryItem {
	private String name,link;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public CategoryItem() {}
	
	public CategoryItem(String name, String link) {
		this.name = name;
		this.link = link;
	}

}
