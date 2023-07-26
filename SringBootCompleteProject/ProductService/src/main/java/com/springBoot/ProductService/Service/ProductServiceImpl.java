package com.springBoot.ProductService.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.ProductService.Entity.Product;
import com.springBoot.ProductService.Exception.ProductServiceCustomException;
import com.springBoot.ProductService.Modal.ProductRequest;
import com.springBoot.ProductService.Modal.ProductResponse;
import com.springBoot.ProductService.Repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("Adding Product....");
		Product product = 
				Product.builder()
				.productName(productRequest.getName())
				.quantity(productRequest.getQuantity())
				.price(productRequest.getPrice())
				.build();
		productRepository.save(product);
		log.info("Product saved successfully");
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductByID(long productId) {
		log.info("Get the product for productId: {}",productId);
		Product product=productRepository.findById(productId).orElseThrow(()->new ProductServiceCustomException("product with given Id is not found","Product Not found"));
		
		ProductResponse productResponse =new ProductResponse();
		
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		log. info("Reducex Quantity {} for ID {}",quantity,productId);
	 Product product=productRepository.findById(productId).orElseThrow(()->new ProductServiceCustomException("product with given ID not found", "Product_NOT_FOUND"));
	
	 if(product.getQuantity()<quantity) {
		 throw new ProductServiceCustomException("Product does not have sufficient Quantity", "INSUFFICIENT_QUANTITY");
	 }
	 
	 product.setQuantity(product.getQuantity()-quantity);
	 productRepository.save(product);
	 log.info("Product Quantity updated successfully");
	 
	}

}
