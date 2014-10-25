package com.banyou.backend.entity;



import javax.persistence.Entity;
import javax.persistence.Table;

//JPA标识
//@Entity
@Table(name = "tag_group")
public class TagGroup extends IdEntity{
private String name;


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
