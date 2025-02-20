package com.ibm.ms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ProductTagId.class)
public class ProductTag {

	@Id
	private int id;
	
	@Id
	private String tag;

	public ProductTag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductTag(int id, String tag) {
		super();
		this.id = id;
		this.tag = tag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "ProductTag [id=" + id + ", tag=" + tag + "]";
	}
	
	
}
