package com.banyou.backend.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//JPA标识
//@Entity
@Table(name = "tag")
public class Tag extends IdEntity{
private String name;
private TagGroup group;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@ManyToOne
@JoinColumn(name="group_id")
public TagGroup getGroup() {
	return group;
}
public void setGroup(TagGroup group) {
	this.group = group;
}




}
