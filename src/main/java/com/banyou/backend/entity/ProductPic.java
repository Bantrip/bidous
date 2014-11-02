package com.banyou.backend.entity;


import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//JPA标识
//@Entity
@Table(name = "prod_imgs")
public class ProductPic extends IdEntity{
private String url;
private int index;
private int is_default;
private int status;
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
public int getIs_default() {
	return is_default;
}
public void setIs_default(int is_default) {
	this.is_default = is_default;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
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
