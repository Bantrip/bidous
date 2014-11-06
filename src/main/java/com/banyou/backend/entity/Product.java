package com.banyou.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//JPA标识
@Entity
@Table(name = "product")
public class Product extends IdEntity{
public final static String PIC_SPLIT=",";
	
private String name;
private String recommand;
private int status;
private BigDecimal price;
private String url;
private int stock;
private String pics;
private List<Dest> dests=Lists.newArrayList();
private List<Tag> tags=Lists.newArrayList();

private List<ProductDesc> descs=Lists.newArrayList();;

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




public String getPics() {
	return pics;
}
public void setPics(String pics) {
	this.pics = pics;
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

//多对多定义
@ManyToMany
@JoinTable(name = "product_has_tag", joinColumns = { @JoinColumn(name = "product_id")}, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
//Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
//集合按id排序
@OrderBy("name ASC")
//缓存策略
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public List<Tag> getTags() {
	return tags;
}
public void setTags(List<Tag> tags) {
	this.tags = tags;
}




@OneToMany(mappedBy="product")
@OrderBy("index ASC")
public List<ProductDesc> getDescs() {
	return descs;
}
public void setDescs(List<ProductDesc> descs) {
	this.descs = descs;
}
@Override
public String toString() {
	return ToStringBuilder.reflectionToString(this);
}
//tools method
private final static List<String> EMPTY_PICS=Collections.emptyList();
@Transient
@JsonIgnore
public List<String> getImages(){
	String[] urls=StringUtils.split(getPics(),PIC_SPLIT);
	return urls==null?EMPTY_PICS:Lists.newArrayList(urls);
}

@Transient
@JsonIgnore
public void setImages(String[] images){
	String pics=StringUtils.join(images,Product.PIC_SPLIT);
	this.setPics(pics);
}


@Transient
@JsonIgnore
public String getDefaultPic(){
	return getImages().isEmpty()?"":getImages().get(0);
}


}
