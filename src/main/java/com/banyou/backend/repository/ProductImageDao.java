/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.repository;

import java.util.List;



import org.springframework.data.repository.PagingAndSortingRepository;

import com.banyou.backend.entity.ProductImage;
public interface ProductImageDao extends PagingAndSortingRepository<ProductImage, Long> {
	List<ProductImage> findByProductId(Long id);
}
