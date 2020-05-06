package com.ibm.ms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	private int id;
	private String name;
	private String shortDescription;
	private double mrp;	
	private ProductCategory category;
	
	@OneToMany(targetEntity = ProductTag.class, mappedBy = "id",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<ProductTag> tags = new ArrayList<ProductTag>();
	
	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Product(int id, String name, String shortDescription,  ProductCategory category,double mrp
			) {
		super();
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.mrp = mrp;
		this.category = category;
		
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getShortDescription() {
		return shortDescription;
	}



	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}



	public double getMrp() {
		return mrp;
	}



	public void setMrp(double mrp) {
		this.mrp = mrp;
	}



	public ProductCategory getCategory() {
		return category;
	}



	public void setCategory(ProductCategory category) {
		this.category = category;
	}



	public List<ProductTag> getTags() {
		return tags;
	}



	public void setTags(List<ProductTag> tags) {
		this.tags = tags;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", shortDescription=" + shortDescription + ", mrp=" + mrp
				+ ", category=" + category + ", tags=" + tags + "]";
	}

	

}
