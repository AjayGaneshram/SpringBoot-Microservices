package com.springBoot.ProductService.Service;

import com.springBoot.ProductService.Modal.ProductRequest;
import com.springBoot.ProductService.Modal.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductByID(long productId);

	void reduceQuantity(long productId, long quantity);

}
