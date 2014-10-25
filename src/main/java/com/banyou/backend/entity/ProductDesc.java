package com.banyou.backend.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//JPA标识
//@Entity
@Table(name = "prod_desc")
public class ProductDesc extends IdEntity{
	private String content;
	private int index;
	private String url;
private Product product;
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

public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@ManyToOne
@JoinColumn(name="product_id")
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}

}
