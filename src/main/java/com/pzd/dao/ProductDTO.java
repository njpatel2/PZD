package com.pzd.dao;

import javax.persistence.ManyToOne;

import com.pzd.entities.Category;

public class ProductDTO {

	private String pName;
	private String pDesc;
	private String pPhoto;
	private float pPrice;
	private float pDiscount;
	private int pQuantity;
	private int categoryId;

	public ProductDTO(String pName, String pDesc, String pPhoto, float pPrice, float pDiscount, int pQuantity,
			int category) {
		super();
		this.pName = pName;
		this.pDesc = pDesc;
		this.pPhoto = pPhoto;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
		this.categoryId = category;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpDesc() {
		return pDesc;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	public String getpPhoto() {
		return pPhoto;
	}

	public void setpPhoto(String pPhoto) {
		this.pPhoto = pPhoto;
	}

	public float getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public float getpDiscount() {
		return pDiscount;
	}

	public void setpDiscount(int pDiscount) {
		this.pDiscount = pDiscount;
	}

	public int getpQuantity() {
		return pQuantity;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}

	@Override
	public String toString() {
		return "ProductDTO [pName=" + pName + ", pDesc=" + pDesc + ", pPhoto=" + pPhoto + ", pPrice=" + pPrice
				+ ", pDiscount=" + pDiscount + ", pQuantity=" + pQuantity + ", category=" + categoryId + "]";
	}

}
