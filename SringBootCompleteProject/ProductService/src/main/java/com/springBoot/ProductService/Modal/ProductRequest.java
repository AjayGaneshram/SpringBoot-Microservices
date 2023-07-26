package com.springBoot.ProductService.Modal;

import lombok.Data;

@Data
public class ProductRequest {

	private String name;
	private long price;
	private long quantity;
}
