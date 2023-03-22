package com.pzd.DTO;

public class ProductDTO {

	private int pId;

	private String pName;
	private String pDesc;
	private String pPhoto;
	private float pPrice;
	private float pDiscount;
	private int pQuantity;
	private CategoryDTO category;

	public ProductDTO(int pId, String pName, String pDesc, String pPhoto, float pPrice, float pDiscount, int pQuantity,
			CategoryDTO category) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pDesc = pDesc;
		this.pPhoto = pPhoto;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
		this.category = category;
	}

	public ProductDTO() {
		super();
	}

	public ProductDTO(String pName, String pDesc, String pPhoto, float pPrice, float pDiscount, int pQuantity,
			CategoryDTO category) {
		super();
		this.pName = pName;
		this.pDesc = pDesc;
		this.pPhoto = pPhoto;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
		this.category = category;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
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

	public void setpPrice(float pPrice2) {
		this.pPrice = pPrice2;
	}

	public float getpDiscount() {
		return pDiscount;
	}

	public void setpDiscount(float pDiscount) {
		this.pDiscount = pDiscount;
	}

	public int getpQuantity() {
		return pQuantity;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO categoryId) {
		this.category = categoryId;
	}

	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}

	@Override
	public String toString() {
		return "ProductDTO [pName=" + pName + ", pDesc=" + pDesc + ", pPhoto=" + pPhoto + ", pPrice=" + pPrice
				+ ", pDiscount=" + pDiscount + ", pQuantity=" + pQuantity + ", category=" + category + "]";
	}

}
