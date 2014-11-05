package com.banyou.backend.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//JPA标识
@Entity
@Table(name = "product_desc")
public class ProductDesc extends IdEntity{
	public final static int TYPE_DESC=0;
	public final static int TYPE_IMG=1;
	private String content;
	private int index;
	private int type;
private Product product;

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
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="product_id")
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}

}
