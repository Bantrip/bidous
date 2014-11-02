package com.banyou.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

//JPA标识
@Entity
@Table(name = "dest")
public class Dest extends IdEntity{
private String name;
private int type;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
@Override
public String toString() {
	return ToStringBuilder.reflectionToString(this);
}




}
