/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.repository;

import com.banyou.backend.entity.ProductImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
public interface ProductImageDao extends PagingAndSortingRepository<ProductImage, Long> {
	List<ProductImage> findByProductId(Long id);
	
	@Modifying
	@Query(value="delete from ProductImage img where img.product.id=?")
	int deleteByProductId(Long id);
}
