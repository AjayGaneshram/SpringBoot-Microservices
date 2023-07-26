package com.springBoot.ProductService.Repository;

import org.springframework.stereotype.Repository;

import com.springBoot.ProductService.Entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
