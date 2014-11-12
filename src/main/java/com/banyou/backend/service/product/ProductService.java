/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.banyou.backend.service.product;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banyou.backend.entity.Product;
import com.banyou.backend.entity.ProductDesc;
import com.banyou.backend.repository.ProductDao;
import com.banyou.backend.repository.ProductDescDao;
import com.banyou.backend.service.account.ShiroDbRealm.ShiroUser;
import com.banyou.backend.web.UserContext;
import com.google.common.collect.Maps;

import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

/**
 * 商品管理类
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class ProductService {

	private static Logger logger = LoggerFactory
			.getLogger(ProductService.class);

	private ProductDao productDao;

	private ProductDescDao productDescDao;

	/**
	 * 获取单个商品信息
	 * 
	 * @param id
	 * @return
	 */
	public Product getProduct(Long id) {

		Product product = productDao.findOne(id);
		checkReadPermission(product, UserContext.getUser());
		return product;
	}

	/**
	 * 
	 * @param pageSize
	 *            分页大小
	 * @param pageNo
	 *            第几页
	 * @return
	 */
	public Page<Product> findProducts(int pageSize, int pageNo, ShiroUser user) {

		Pageable page = pageSize > 0 ? new PageRequest(pageNo - 1, pageSize)
				: null;
		Specifications<Product> spec = null;
		if (!UserContext.isSuper()) {
			Map<String, SearchFilter> filters = Maps.newHashMap();

			List<SearchFilter> creater = Arrays.asList(new SearchFilter(
					"creater", Operator.EQ, user.id));
			spec = Specifications.where(DynamicSpecifications.bySearchFilter(
					creater, Product.class));

			if (user.merchantId != null) {
				List<SearchFilter> merchant = Arrays.asList(new SearchFilter(
						"merchantId", Operator.EQ, user.merchantId));
				spec = spec.or(DynamicSpecifications.bySearchFilter(merchant,
						Product.class));

			}

		}

		return productDao.findAll(spec, page);
	}

	/**
	 * 
	 * @param product
	 */
	public void auditProduct(Product product, ShiroUser user) {
		checkWritePermission(product, user);
		productDao.updateStatus(product.getId(), Product.STATUS_IN_AUDIT,
				product.getStatus());

	}

	/**
	 * 审核通过
	 * 
	 * @param product
	 * @param user
	 */
	public void passProduct(Product product, ShiroUser user) {

		checkWritePermission(product, user);
		productDao.updateStatus(product.getId(), Product.STATUS_OK,
				product.getStatus());
	}

	/**
	 * 
	 * @param product
	 */
	public void deleteProduct(Product product, ShiroUser user) {
		product.getTags().clear();
		product.getDests().clear();
		productDao.delete(product);
		productDescDao.deleteByProductId(product.getId());

	}

	/**
	 * 保存商品
	 * 
	 * @param product
	 */
	public void saveProduct(Product product, List<ProductDesc> descs,
			ShiroUser user) {
		if (product.getId() != null) {
			// 检查是否有操作权
			checkWritePermission(product, user);
			// 非管理员修改，且状态是审核通过的，将状态设置为待审核
			if (product.getStatus() >= Product.STATUS_OK
					&& !UserContext.isSuper()) {
				product.setStatus(Product.STATUS_IN_AUDIT);
			}
			// 删除关联的表
			productDescDao.deleteByProductId(product.getId());
		} else {
			if (UserContext.isSuper()) {
				product.setStatus(Product.STATUS_OK);
			}
			product.setCreater(user.id);
			product.setCreateTime(new Date());
		}

		productDao.save(product);

		for (ProductDesc desc : descs) {
			desc.setProduct(product);
			productDescDao.save(desc);
		}

	}

	/**
	 * 检查是否有读的权限
	 * 
	 * @param product
	 * @param user
	 */
	private void checkReadPermission(Product product, ShiroUser user) {
		//创建者，商家，或者超级用户有写的权限
		if (!product.isCreater(user.id) && !product.isSeller(user.merchantId)
				&& !UserContext.isSuper()) {
			throw new ShiroException("没有权限");
		}
	}

	/**
	 * 检查是否写的权限
	 * 
	 * @param product
	 * @param user
	 */
	private void checkWritePermission(Product product, ShiroUser user) {
		//只有商家或者超级用户有写的权限
		if (!product.isSeller(user.merchantId) && !UserContext.isSuper()) {
			throw new ShiroException("没有权限");
		}
	}

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Autowired
	public void setProductDescDao(ProductDescDao productDescDao) {
		this.productDescDao = productDescDao;
	}

}
