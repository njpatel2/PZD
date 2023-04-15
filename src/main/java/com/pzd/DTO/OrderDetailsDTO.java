package com.pzd.DTO;


public class OrderDetailsDTO {

	private OrderDTO OrderDTO;
	
	private ProductDTO productDTO;
	
	private int productQuantity;
	
	public OrderDetailsDTO() {
		super();
	}

	public OrderDetailsDTO(OrderDTO orderDTO, ProductDTO productDTO , int productQuantity) {
		super();
		OrderDTO = orderDTO;
		this.productDTO = productDTO;
		this.productQuantity = productQuantity;
	}

	public OrderDTO getOrderDTO() {
		return OrderDTO;
	}

	public void setOrderDTO(OrderDTO orderDTO) {
		OrderDTO = orderDTO;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	
	

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Override
	public String toString() {
		return "OrderDetailsDTO [OrderDTO=" + OrderDTO + ", productDTO=" + productDTO + "]";
	}
	
	
	
	

	
}
