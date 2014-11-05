package com.banyou.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


//JPA标识
@Entity
@Table(name = "prod_imgs")
public class ProductImage extends IdEntity{
private String url;
private int index;
private Product product;


@NotNull
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name = "product_id")
public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public int getIndex() {
	return index;
}

public void setIndex(int index) {
	this.index = index;
}

@Override
public String toString() {
	return "ProductImage [url=" + url + ", index=" + index + "]";
}





}
