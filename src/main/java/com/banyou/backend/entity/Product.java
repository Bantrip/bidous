package com.banyou.backend.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.Lists;

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
private List<Dest> dests=Lists.newArrayList();
private List<ProductImage> images=Lists.newArrayList();
private List<Tag> tags=Lists.newArrayList();

//private List<ProductDesc> descs;
@NotEmpty
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
@NotNull
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
// 多对多定义
@ManyToMany
@JoinTable(name = "product_has_dest", joinColumns = { @JoinColumn(name = "product_id")}, inverseJoinColumns = { @JoinColumn(name = "dest_id") })
// Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
// 集合按id排序
@OrderBy("name ASC")
// 缓存策略
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public List<Dest> getDests() {
	return dests;
}
public void setDests(List<Dest> dests) {
	this.dests = dests;
}


@Transient
public List<Tag> getTags() {
	return tags;
}
public void setTags(List<Tag> tags) {
	this.tags = tags;
}
@NotEmpty
@OneToMany(mappedBy="product")
@OrderBy("index ASC")
public List<ProductImage> getImages() {
	return images;
}
public void setImages(List<ProductImage> images) {
	this.images = images;
}
@Override
public String toString() {
	return ToStringBuilder.reflectionToString(this);
}
//tools method
@Transient
public String getDefaultPic(){
	return getImages().isEmpty()?"":getImages().get(0).getUrl();
}


}
