package com.pzd.dao;

public class CategoryDTO {
	
	private String categoryTitle;
	private String categoryDescription;
	
	
	
	public CategoryDTO(String categoryTitle, String categoryDescription) {
		super();
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	@Override
	public String toString() {
		return "CategoryDTO [categoryTitle=" + categoryTitle + ", categoryDescription=" + categoryDescription + "]";
	}


}
