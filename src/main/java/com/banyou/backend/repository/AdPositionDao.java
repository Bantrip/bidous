/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.repository;


import com.banyou.backend.entity.AdPosition;
import com.banyou.backend.entity.Dest;
import com.banyou.backend.entity.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdPositionDao extends PagingAndSortingRepository<AdPosition, Long> {
	AdPosition findByCode(String code);
}
