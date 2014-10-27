package com.banyou.backend.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

//JPA标识
@Entity
@Table(name = "product")
public class Product extends IdEntity{
private String name;
private String recommand;
private int status;
private BigDecimal price;
private String url;
private int stock;
//private List<ProductDesc> desc;
//private List<ProductPic> pics;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getRecommand() {
	return recommand;
}
public void setRecommand(String recommand) {
	this.recommand = recommand;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public BigDecimal getPrice() {
	return price;
}
public void setPrice(BigDecimal price) {
	this.price = price;
}
public String getUrl() {
	return url;
}
public void setUrl(String target) {
	this.url = target;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}

@Override
public String toString() {
	return ToStringBuilder.reflectionToString(this);
}
}
